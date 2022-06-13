package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	public Optional<User> findById(Long id);
	
}