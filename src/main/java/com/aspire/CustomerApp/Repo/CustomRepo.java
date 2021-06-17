package com.aspire.CustomerApp.Repo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspire.CustomerApp.Data.Customer;



@Repository
public interface CustomRepo extends MongoRepository<Customer, String> {
	
	List<Customer>  findByEmailId(String emailId);

	//@Query("{'address.city': ?}")
	List<Customer>  findByAddressCity(String city);

	void deleteById(long id);

	Customer findByName(String name);
	Customer findById(int id);
	
	@SuppressWarnings("unchecked")
	Customer save(Customer customer);
}
