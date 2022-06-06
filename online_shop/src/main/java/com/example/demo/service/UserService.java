package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired //Carica da solo un istanza di PersonaRepository
	private UserRepository ur;
	
	@Transactional //Ci pensa springboot ad aprire e chiudere la transazione
	public void save(User user) { ur.save(user); }
	
	//Interrogazione, non transazionale
	public User findById (Long id) { return ur.findById(id).get(); }
	
	public List<User> findAll() {
		List<User> utenti = new ArrayList<User>();
		for (User c : ur.findAll()) { utenti.add(c); } //La findAll ritorna un iteratore, non una lista
		return utenti;
	}
}
