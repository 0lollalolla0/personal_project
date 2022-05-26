package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Component
public class UserValidator implements Validator {

	@SuppressWarnings("unused")
	@Autowired
	private UserService us;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) { }

}
