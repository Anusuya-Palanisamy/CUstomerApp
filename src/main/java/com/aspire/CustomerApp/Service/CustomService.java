package com.aspire.CustomerApp.Service;

import java.util.List;

import com.aspire.CustomerApp.Data.Customer;

public interface CustomService {

	public Customer createCustomer(Customer customer);

	public List<Customer> getAllCustomer();

	List<Customer> getByEmailId(String emailId);

	List<Customer> getByCity(String city);

	public void deleteByCustomerId(long id);

	public void deleteAllCustomers();

	Customer findByCustomerName(String name,Customer customer);
	Customer findByCustomerId(int id, String name);

}
