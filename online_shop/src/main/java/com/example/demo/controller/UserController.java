package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.AddressValidator;
import com.example.demo.controller.validator.CredentialsValidator;
import com.example.demo.controller.validator.UserValidator;
import com.example.demo.model.Address;
import com.example.demo.model.Basket;
import com.example.demo.model.Comment;
import com.example.demo.model.Credentials;
import com.example.demo.model.Product;
import com.example.demo.model.ProductChosen;
import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.BasketService;
import com.example.demo.service.CommentService;
import com.example.demo.service.CredentialsService;
import com.example.demo.service.ProductChosenService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService us;
	
	@Autowired
	private UserValidator uv;
	
	@Autowired
	private AddressValidator av;

	@Autowired
	private CredentialsValidator cv;

	@Autowired
	private AddressService as;

	@Autowired
	private CredentialsService cs;

	@Autowired
	private ProductService ps;
	
	@Autowired
	private ProductChosenService pcs;
	
	@Autowired
	private BasketService bs;
	
	@Autowired
	private CommentService commentService;

	private User currentUser;

	private Product currentProduct;
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("credentials", new Credentials());
		return "login.html";
	}
	
	@GetMapping("/signin")
	public String getSignin(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("address", new Address());
		model.addAttribute("credentials", new Credentials());
		return "signin.html";
	}
	
	@PostMapping("/signin")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult ubr,
			@Valid @ModelAttribute("address") Address address, BindingResult abr,
			@Valid @ModelAttribute("credentials") Credentials credentials, BindingResult cbr,
			Model model) {
		uv.validate(user, ubr);
		av.validate(address, abr);
		cv.validate(credentials, cbr);
		if(!ubr.hasErrors() && !abr.hasErrors() && !cbr.hasErrors()) {
			as.save(address);
			user.setAddress(address);
			us.save(user);
			credentials.setUser(user);
			cs.save(credentials);
			return "successfulregistration.html";
		}
		return "signin.html";
	}
	
	@GetMapping("/successfulregistration")
	private String getSuccessfulRegistration(Model model) {
		return "successfulregistration.html";
	}
	
	@GetMapping("/home")
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = this.cs.getCredentials(userDetails.getUsername());
    	this.currentUser = credentials.getUser();
    	model.addAttribute(this.currentUser);
    	model.addAttribute("products", this.ps.findAll());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home.html";
        }
        return "home.html";
    }
	
	@GetMapping("/logout")
	public String logOut(Model model) {
		return "index.html";
	}
	
	@GetMapping("/addressof/{id}")
	public String changeAddress(@PathVariable("id") Long id, Model model) {
		this.currentUser = this.us.findById(id);
		model.addAttribute("address", this.currentUser.getAddress());
		return "changeaddress.html";
	}
	
	@PostMapping("/addresschange")
	public String changeAddress(@ModelAttribute("address") Address address, BindingResult br, Model model) {
		this.av.validate(address, br);
		if(!br.hasErrors()) {
			this.as.save(address);
			this.currentUser.setAddress(address);
			this.us.save(this.currentUser);
			model.addAttribute("user", this.currentUser);
			return "home.html";
		}
		return "changeaddress.html";
	}
	
	@GetMapping("/toyouraccount/{id}")
	public String getAccount(@PathVariable("id") Long id, Model model) {
		this.currentUser = this.us.findById(id);
		model.addAttribute("user", this.currentUser);
		model.addAttribute("address", this.currentUser.getAddress());
		model.addAttribute("orders", this.currentUser.getOrders());
		return "useraccount.html";
	}
	
	@GetMapping("/basketof/{id}")
	public String getBasket(@PathVariable("id") Long id, Model model) {
		this.currentUser = this.us.findById(id);
		model.addAttribute("basket", this.currentUser.getBasket());
		return "basket.html";
	}
	
	@GetMapping("/onemore/{id}")
	public String oneMore(@PathVariable("id") Long id, Model model) {
		Basket b = this.currentUser.getBasket();
		ProductChosen p = this.pcs.findById(id);
		if(p.getProduct().getQuantityAvailable() >= (p.getQuantityChosen()+1)) {
			p.setQuantityChosen(p.getQuantityChosen()+1);
			this.pcs.save(p);
			List<ProductChosen> products = b.getProducts();
			Integer i = 0;
			for(ProductChosen pc : products) {
				if(pc.getName().equals(p.getName())) {
					products.set(i, p);
				}
				i++;
			}
			b.setProducts(products);
			b.calculateTotal();
			this.bs.save(b);
			this.currentUser.setBasket(b);
			this.us.save(this.currentUser);
		}
		model.addAttribute("basket", b);
		return "basket.html";
	}
	
	@GetMapping("/oneless/{id}")
	public String oneLess(@PathVariable("id") Long id, Model model) {
		Basket b = this.currentUser.getBasket();
		ProductChosen p = this.pcs.findById(id);
		if(p.getQuantityChosen()-1 > 0) {
			p.setQuantityChosen(p.getQuantityChosen()-1);
			this.pcs.save(p);
			List<ProductChosen> products = b.getProducts();
			Integer i = 0;
			for(ProductChosen pc : products) {
				if(pc.getName().equals(p.getName())) {
					products.set(i, p);
				}
				i++;
			}
			p.getProduct().setQuantityAvailable(p.getProduct().getQuantityAvailable()+1);
			b.setProducts(products);
			b.calculateTotal();
			this.bs.save(b);
			this.currentUser.setBasket(b);
			this.us.save(this.currentUser); 
		}
		model.addAttribute("basket", b);
		return "basket.html";
	}
	
	@GetMapping("/todeleteproduct/{id}")
	public String deleteFromBasket(@PathVariable("id") Long id, Model model) {
		Basket b = this.currentUser.getBasket();
		ProductChosen p = this.pcs.findById(id);
		
		List<ProductChosen> products = b.getProducts();
		for(Iterator<ProductChosen> it = products.iterator(); it.hasNext();) {
			ProductChosen pc = it.next();
			if(pc.getName().equals(p.getName())) {
				it.remove();
			}
		}
		p.getProduct().setQuantityAvailable(p.getProduct().getQuantityAvailable()-1);
		b.setProducts(products);
	    this.bs.save(b);
	    b.calculateTotal();
		this.currentUser.setBasket(b);
		
		this.pcs.deleteById(id);
		model.addAttribute("basket", b);
		return "basket.html";
	}
	
	@GetMapping("/tohome")
	public String getHomePage(Model model) {
		model.addAttribute("user", this.currentUser);
		model.addAttribute("products", this.ps.findAll());
		return "home.html";
	}
	
	@GetMapping("/openproduct/{id}")
	public String getProduct(@PathVariable("id") Long id, Model model) {
	    Product p = this.ps.findById(id);
	    this.currentProduct = p;
		model.addAttribute("product", p);
		model.addAttribute("comment", new Comment(new String(), this.currentUser));
		return "product.html";
	}
	
	@GetMapping("/toput/{id}")
	public String putProductOnTheBasket(@PathVariable("id") Long id, Model model) {
		Product p = this.ps.findById(id);
		ProductChosen pc = new ProductChosen(p, 1);
		this.pcs.save(pc);
		this.currentUser.getBasket().getProducts().add(pc); //O senno separo il basket
		model.addAttribute("user", this.currentUser);
		model.addAttribute("products", this.ps.findAll()); //GESTIRE DUPLICATI
		return "home.html";
	}
	
	@PostMapping("/comment")
	public String comment (@ModelAttribute("comment") Comment comment, BindingResult br, Model model) {
		comment.setAuthor(this.currentUser);
		this.commentService.save(comment);
		this.currentProduct.addComment(comment);
		this.ps.save(this.currentProduct);
		model.addAttribute("product", this.currentProduct);
		model.addAttribute("comment", new Comment(new String(), this.currentUser));
		return "product.html";
	}
	
	
	
}