package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User author;
	
	private String content;
	
	public User getAuthor() { return author;}
	
	public void setAuthor(User author) { this.author = author; }
	
	public String getContent() { return content; }
	
	public void setContent(String content) { this.content = content; }
	
}
