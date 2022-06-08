package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Fetch(value = org.hibernate.annotations.FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ProductChosen> products;
	
	private Float total;

	public Basket() {
		this.products = new ArrayList<>();
	}
	
	public List<ProductChosen> getProducts() { return products; }

	public void addProduct(ProductChosen p) { this.products.add(p); }

	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float price) { this.total = price; }
	
	public void calculateTotal() {
		  Float k = 0F;
		  for(ProductChosen p : products) {
			  k += (p.getPrice() * p.getQuantityChosen());
		}
		  this.total = k;
	}

	public void removeProduct(ProductChosen p) {
		this.products.remove(p);
	}

	public void setProducts(List<ProductChosen> products) {
		this.products = products;
	}
	
}
