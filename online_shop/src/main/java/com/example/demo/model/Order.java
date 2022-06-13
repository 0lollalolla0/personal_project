package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "orders") // cambiamo nome perchè in postgres order e' una parola riservata
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER)
    private List<ProductChosen> products;
	
	private Float total;
	
	private LocalDateTime dateTime;

	public Order() { 
		this.products = new ArrayList<>();
	}
	
	public Order(List<ProductChosen> products, Float total) {
		this.products = new ArrayList<>();
		this.products.addAll(products);
		this.total = total;
	}
	
	public List<ProductChosen> getProducts() { return products; }

	public Float getTotal() { return total; }

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
