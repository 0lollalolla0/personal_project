package com.example.demo.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
public class ProductChosen extends Product {
	
	private Long id = super.getId();
	
	@Min(1)
	private Integer quantityChosen;

	public Integer getQuantityChosen() { return quantityChosen; }

	public void setQuantityChosen(Integer quantityChosen) { this.quantityChosen = quantityChosen; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
