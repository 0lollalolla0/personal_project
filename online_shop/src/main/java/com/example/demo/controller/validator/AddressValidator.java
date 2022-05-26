package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;

@Component
public class AddressValidator implements Validator {
	
	@SuppressWarnings("unused")
	@Autowired
	private AddressService as;

	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) { }

}
