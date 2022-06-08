package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductChosen;
import com.example.demo.repository.ProductChosenRepository;

@Service
public class ProductChosenService {
	
	@Autowired
	private ProductChosenRepository pcr;
	
	@Transactional
	public void save(ProductChosen pc) {
		this.pcr.save(pc);
	}

	public ProductChosen findById(Long id) {
		return this.pcr.findById(id).get();
	}

	@Transactional
	public void deleteById(Long id) {
		this.pcr.deleteById(id);
	}

	public List<ProductChosen> findAll() {
		List<ProductChosen> ps = new ArrayList<>();
		for(ProductChosen pc : this.pcr.findAll()) {
			ps.add(pc);
		}
		return ps;
	}
}
