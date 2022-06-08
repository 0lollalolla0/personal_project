package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository ar;
	
	@Transactional //Ci pensa springboot ad aprire e chiudere la transazione
	public void save(Address address) { ar.save(address); }
	
	//Interrogazione, non transazionale
	public Address findById (Long id) { return ar.findById(id).get(); }
	
	public List<Address> findAll() {
		List<Address> indirizzi = new ArrayList<Address>();
		for (Address a : ar.findAll()) { indirizzi.add(a); } //La findAll ritorna un iteratore, non una lista
		return indirizzi;
	}
	
	public boolean AlreadyExists(Address a) {
		return ar.existsByStreetAndNumberAndCityAndState(a.getStreet(), a.getNumber(), a.getCity(), a.getState());
	}
	
}
