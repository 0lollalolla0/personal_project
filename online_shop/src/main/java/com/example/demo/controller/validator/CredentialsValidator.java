package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.demo.model.Credentials;
import com.example.demo.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator {
	
	final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 6;

	@Autowired
	private CredentialsService cs;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Credentials.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Credentials c = (Credentials) target;
	        
		if(this.cs.existsByEmail(c.getEmail())) {
			errors.rejectValue("email", "email.duplicato");
		}
		if(this.cs.existsByUsername(c.getUsername())) {
			errors.rejectValue("username", "username.duplicato");
		}
		String username = c.getUsername().trim();
	    String password = c.getPassword().trim();
	    
	    if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH)
            errors.rejectValue("username", "username.size");

        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
            errors.rejectValue("password", "password.size");
	}
}
