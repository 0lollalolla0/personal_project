package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductChosen;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pr;

	public List<Product> findAll() {
		List<Product> products = new ArrayList<Product>();
		for (Product p : this.pr.findAll()) {
			products.add(p);
		}
		return products;
	}

	@Transactional
	public void save(Product p1) {
		this.pr.save(p1);
	}

	public Product findById(Long id) {
		return this.pr.findById(id).get();
	}

}
