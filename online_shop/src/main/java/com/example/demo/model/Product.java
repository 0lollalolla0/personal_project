package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Float price;
	
	private String description;
	
	@Min(0)
	private Integer quantityAvailable;
	
	@OneToMany
	private List<Comment> comments;

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Float getPrice() { return price; }

	public void setPrice(Float price) { this.price = price; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public Integer getQuantityAvailable() { return quantityAvailable; }

	public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
	
}
