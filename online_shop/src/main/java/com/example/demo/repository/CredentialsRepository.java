package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long>{

	public boolean existsByEmail(String email);

	public boolean existsByUsername(String username);

	public Optional<Credentials> findByUsername(String username);
	
}
