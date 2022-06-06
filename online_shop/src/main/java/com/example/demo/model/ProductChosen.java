package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductChosen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Product product;
	
	private Integer quantityChosen;
	
	public ProductChosen() {}
	
	public ProductChosen(Product p, Integer q) {
		this.product = p;
		this.quantityChosen = q;
	}

	public Integer getQuantityChosen() { return quantityChosen; }

	public void setQuantityChosen(Integer quantityChosen) { this.quantityChosen = quantityChosen; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Float getPrice() {
		return product.getPrice();
	}
	
	public String getName() {
		return this.product.getName();
	}
	
}
