package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository cr;
	
	@Transactional
	public void save (Comment c) {
		this.cr.save(c);
	}
}
