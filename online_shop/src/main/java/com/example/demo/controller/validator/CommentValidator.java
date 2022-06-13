package com.example.demo.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Comment;

@Component
public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Comment c = (Comment) target;
		if(c.getContent().equals("")) errors.rejectValue("content", null);
	}

}
