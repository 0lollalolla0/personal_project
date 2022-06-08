package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Comment;

@Component
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
