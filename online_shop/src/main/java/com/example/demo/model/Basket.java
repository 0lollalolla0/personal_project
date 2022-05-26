package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private List<ProductChosen> products;
	
	private Float total;

	public List<ProductChosen> getProducts() { return products; }

	public void addProduct(ProductChosen p) { this.products.add(p); }

	public Float getPrice() { return total; }

	public void setPrice(Float price) { this.total = price; }
	
}
