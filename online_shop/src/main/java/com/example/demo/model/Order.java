package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders") // cambiamo nome perchè in postgres order e' una parola riservata
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
    private List<ProductChosen> products;
	
	private Float total;

	public Order() { }
	
	public Order(List<ProductChosen> products, Float total) {
		this.products = products;
		this.total = total;
	}
	
	public List<ProductChosen> getProducts() { return products; }

	public Float getTotal() { return total; }

}
