package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository or;
	
	@Transactional
	public void save(Order o) {
		this.or.save(o);
	}
}
