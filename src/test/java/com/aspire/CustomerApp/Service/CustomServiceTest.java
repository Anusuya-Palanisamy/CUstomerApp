package com.aspire.CustomerApp.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.aspire.CustomerApp.Data.Address;
import com.aspire.CustomerApp.Data.Customer;
import com.aspire.CustomerApp.Repo.CustomRepo;

@SpringBootTest
class CustomServiceTest {
	@Mock
	CustomRepo customRepository;

	@InjectMocks
	CustmServiceImpl customServiveImpl;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	public void TestcreateCustomer() {
		Customer customer = new Customer(4, "Gowtheesh", "Customer", "gowtheesh@gmail.com",
				new Address("Udumalpet", "TamilNadu", "India"));
		// List<Customer> customerList=new ArrayList<>();
		when(customRepository.save(customer)).thenReturn(customer);
		// assertThat(customServiveImpl.createCustomer(customer)).isEqualTo(customer);

	}

	@Test
	public void TestgetAllCustomer() {
		Customer customer1 = new Customer(4, "Gowtheesh", "Customer", "gowtheesh@gmail.com",
				new Address("Udumalpet", "TamilNadu", "India"));
		Customer customer2 = new Customer(0, "Anu", "Customer", "anu@gmail.com",
				new Address("cbe", "TamilNadu", "India"));
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		when(customRepository.findAll()).thenReturn(customerList);
		assertThat(customServiveImpl.getAllCustomer()).isEqualTo(customerList);

	}

	@Test
	public void TestfindByCustomerName() {
		Customer customer = new Customer(4, "Gowtheesh", "Customer", "gowtheesh@gmail.com",
				new Address("Udumalpet", "TamilNadu", "India"));
		Mockito.when(customRepository.findByName("Gowtheesh")).thenReturn(customer);
		customer.setName("Anu");
		when(customRepository.save(customer)).thenReturn(customer);

	}

	@Test
	public void TestgetByEmailId() {
		Customer customer = new Customer(4, "Gowtheesh", "Customer", "gowtheesh@gmail.com",
				new Address("Udumalpet", "TamilNadu", "India"));
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		when(customRepository.findByEmailId("gowtheesh@gmail.com")).thenReturn(customerList);

	}

	@Test
	public void TestgetByCity() {
		Customer customer = new Customer(4, "Gowtheesh", "Customer", "gowtheesh@gmail.com",
				new Address("Udumalpet", "TamilNadu", "India"));
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		Mockito.when(customRepository.findByAddressCity("Udumalpet")).thenReturn(customerList);

	}

}
