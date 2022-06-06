package com.example.demo.start;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.model.Address;
import com.example.demo.model.Basket;
import com.example.demo.model.Credentials;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.ProductChosen;
import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.BasketService;
import com.example.demo.service.CredentialsService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductChosenService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private CredentialsService credentialsService;
	
	@Autowired
	private AddressService as;

	@Autowired
	private UserService us;

	@Autowired
	private ProductService ps;

	@Autowired
	private ProductChosenService pcs;

	@Autowired
	private BasketService bs;

	@Autowired
	private OrderService os;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	
    	Address address1 = new Address("Via Vulci", 13, "Santa Marinella", "Italy");
    	this.as.save(address1);
    	
    	Product p1 = new Product();
    	p1.setName("LEOPARD_2A6");
    	p1.setPrice(1F);
    	p1.setQuantityAvailable(1);
    	p1.setDescription("Questo è il primo prodotto");
    	
    	Product p2 = new Product();
    	p2.setName("SD_KFZ_251.1 AUSF_A");
    	p2.setPrice(2F);
    	p2.setQuantityAvailable(2);
    	p2.setDescription("Questo è il secondo prodotto");
    	
    	Product p3 = new Product();
    	p3.setName("SPZ_MARDER_1A3");
    	p3.setPrice(3F);
    	p3.setQuantityAvailable(3);
    	p3.setDescription("Questo è il terzo prodotto");
    	
    	this.ps.save(p1);
    	this.ps.save(p2);
    	this.ps.save(p3);
    	
    	ProductChosen pc1 = new ProductChosen(p1, 1);
    	ProductChosen pc2 = new ProductChosen(p2, 2);
    	ProductChosen pc3 = new ProductChosen(p3, 3);
    	
    	this.pcs.save(pc1);
    	this.pcs.save(pc2);
    	this.pcs.save(pc3);
    	
    	Basket b = new Basket();
    	b.addProduct(pc1);
    	b.addProduct(pc2);
    	b.addProduct(pc3);
    	b.calculateTotal();
    	this.bs.save(b);
    	
    	List<ProductChosen> lista1 = new ArrayList<>();
    	lista1.add(pc1);
    	lista1.add(pc2);
    	
    	List<ProductChosen> lista2 = new ArrayList<>();
    	lista2.add(pc3);
    	
    	Order o1 = new Order(lista1, 5F);
    	this.os.save(o1);
    	
    	Order o2 = new Order(lista2, 9F);
    	this.os.save(o2);
    	
    	User user = new User();
    	user.setFirstName("Mario");
    	user.setLastName("Rossi");
    	user.setAddress(address1);
    	user.setBasket(b);
    	user.addOrder(o1);
    	user.addOrder(o2);
    	this.us.save(user);
    	
    	//Queste sono le credenziali degli user
    	Credentials credentials1 = new Credentials("loryc", "lory.ca@live.it", "ciaociao", "USER_ROLE");
    	credentials1.setUser(user);
    	credentialsService.save(credentials1);
    }

}
