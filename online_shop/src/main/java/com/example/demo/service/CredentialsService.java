package com.example.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Credentials;
import com.example.demo.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired
	private CredentialsRepository cr;
	
	@Autowired
    protected PasswordEncoder passwordEncoder;

	@Transactional
	public void save(@Valid Credentials credentials) {
		credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        this.cr.save(credentials);
	}

	public boolean existsByEmail(String email) {
		return this.cr.existsByEmail(email);
	}

	public boolean existsByUsername(String username) {
		return this.cr.existsByUsername(username);
	}
	
	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.cr.findByUsername(username);
		return result.orElse(null);
	}

}
