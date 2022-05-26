package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

	public boolean existsByStreetAndNumberAndCityAndState(String street, Integer number, String city, String state);
}
