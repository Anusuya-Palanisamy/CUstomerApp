package com.aspire.CustomerApp.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aspire.CustomerApp.CustomerApplication;
import com.aspire.CustomerApp.Data.Customer;
import com.aspire.CustomerApp.Data.Sequence;
import com.aspire.CustomerApp.Repo.CustomRepo;

@Component
@Service("CustomService")

public class CustmServiceImpl implements CustomService {

	@Autowired
	CustomRepo customRepository;
	@Autowired
	Sequence sequence;

	@Override
	public Customer createCustomer(Customer customer) {
		
		customer.setId(sequence.getNextCustomerSequence("customerSeq"));
		Customer customerList =customRepository.save(customer);
		return customerList;
	}

	

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customer = customRepository.findAll();
		return customer.stream().sorted(Comparator.comparing(Customer::getName).reversed())
				.collect(Collectors.toList());

	}

	@Override
	public List<Customer> getByEmailId(String emailId) {
		System.out.println("ControolerExp");
		List<Customer> customer = customRepository.findByEmailId(emailId);
		return customer.stream().sorted(Comparator.comparing(Customer::getName).reversed())
				.collect(Collectors.toList());

	}

	@Override
	public List<Customer> getByCity(String city) {
		System.out.println("ControolerExp");
		List<Customer> customer = customRepository.findByAddressCity(city);
		return customer.stream().sorted(Comparator.comparing(Customer::getName).reversed())
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAllCustomers() {
		customRepository.deleteAll();
	}

	@Override
	public void deleteByCustomerId(long id) {
		customRepository.deleteById(id);
	}

	@Override
	public Customer findByCustomerName(String name,Customer customer) {
		System.out.println("Name got it");
		Customer customerData= customRepository.findByName(name);
		customerData.setName(customer.getName());
		customerData.setEmailId(customer.getEmailId());
		customerData.setType(customer.getType());
		customerData.setAddress(customer.getAddress());
		return customRepository.save(customerData);
		 
		
	}
	
	@Override
	public Customer findByCustomerId(int id, String name) {
		System.out.println("Name got it");
		Customer customer = customRepository.findById(id);
		customer.setName(name);
		return customRepository.save(customer);
	}

}
