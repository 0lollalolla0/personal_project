package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Product;

@Component
public interface ProductRepository extends CrudRepository<Product, Long> {

}
