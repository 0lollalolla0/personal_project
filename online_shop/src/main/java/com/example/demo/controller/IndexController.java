package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;

@Controller
public class IndexController {
	
	@GetMapping("/index/login")
	public String getLogin(Model model) { return "login.html"; }
	
	@GetMapping("/index/signin")
	public String getSignin(Model model) {
		model.addAttribute("user", new User());
		return "signin.html";
		}
	
}
