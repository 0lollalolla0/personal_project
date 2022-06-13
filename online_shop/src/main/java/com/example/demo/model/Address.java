package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String street;
	
	@NotNull
	private Integer number;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String state;

	public Address() {}
	
	public Address(String street, Integer number, String city, String state) {
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
	}
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getStreet() { return street; }

	public void setStreet(String street) { this.street = street; }

	public Integer getNumber() { return number; }

	public void setNumber(Integer number) { this.number = number; }

	public String getCity() { return city; }

	public void setCity(String city) { this.city = city; }

	public String getState() { return state; }

	public void setState(String state) { this.state = state; }
	
}
