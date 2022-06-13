package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Basket;

public interface BasketRepository extends CrudRepository<Basket, Long> {
	
}
