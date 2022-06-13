package com.example.demo.model;

import java.util.ArrayList;
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
	
	private String name;

	@Column(nullable = false)
	private Float price;
	
	private String description;
	
	@Min(0)
	private Integer quantityAvailable;
	
	@OneToMany
	private List<Comment> comments;
	
	public Product() {
		this.comments = new ArrayList<>();
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public Float getPrice() { return price; }

	public void setPrice(Float price) { this.price = price; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public Integer getQuantityAvailable() { return quantityAvailable; }

	public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment c) {
		this.comments.add(c);
	}
	
}
