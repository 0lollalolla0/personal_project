package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.AddressValidator;
import com.example.demo.controller.validator.CredentialsValidator;
import com.example.demo.controller.validator.UserValidator;
import com.example.demo.model.Address;
import com.example.demo.model.Credentials;
import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.CredentialsService;
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
	
	@GetMapping("/successfulregistration")
	private String getSuccessfulRegistration(Model model) {
		return "successfulregistration.html";
	}
	
	@PostMapping("/successfulregistration")
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
	
	@GetMapping("/home")
    public String defaultAfterLogin(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = this.cs.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home.html";
        }
        return "home.html";
    }
	
}
