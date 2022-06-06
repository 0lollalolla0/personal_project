package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Basket;
import com.example.demo.repository.BasketRepository;

@Service
public class BasketService {
	
	@Autowired
	private BasketRepository br;
	
	@Transactional
	public void save(Basket b) {
		this.br.save(b);
	}
}
