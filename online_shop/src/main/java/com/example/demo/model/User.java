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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@OneToOne
	private Address address;

	@OneToOne(cascade = {CascadeType.MERGE})
	private Basket basket;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Order> orders;
	
	public User() {
		this.orders = new ArrayList<>();
	}
	
	public User(String firstName, String lastName) {
		this.orders = new ArrayList<>();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Basket getBasket() { return basket; }

	public void setBasket(Basket basket) { this.basket = basket; }

	public List<Order> getOrders() { return orders; }

	public void addOrder(Order o) { this.orders.add(o); }
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
