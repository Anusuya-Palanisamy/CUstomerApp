package com.aspire.CustomerApp.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.aspire.CustomerApp.Data.Customer;
import com.aspire.CustomerApp.Data.Image;
import com.aspire.CustomerApp.Service.CustmServiceImpl;
import com.aspire.CustomerApp.Service.CustomService;
import com.aspire.CustomerApp.Service.ImageService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/API")
public class CustomController {

	@Autowired
	CustomService customServiveImpl;
	/*
	 * Logger File
	 */
	Logger log = LoggerFactory.getLogger(CustmServiceImpl.class);

	/**
	 * Method to save Customers in the db.
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping("/Customers")
	public Customer create(@RequestBody Customer customer) {
		System.out.println("Customer Rest controller called");
		log.info("Customer hass Entered");
		log.info("Customer Recorded insert Successfully");
		System.out.println("Customer Rest controller Ended");
		Customer customerData = customServiveImpl.createCustomer(customer);
		return customerData;

	}

	/**
	 * Method to fetch all Customers from the db.
	 * 
	 * @return
	 */
	@GetMapping("/CustomerData")
	public List<Customer> getAllData() {
		log.debug("Customer get all recorded");
		List<Customer> customerData = customServiveImpl.getAllCustomer();
		return customerData;
	}

	/*
	 * @GetMapping("/CustomersRest") public List<Customer> getAllRestData() {
	 * System.out.println("Customer Rest controller called");
	 * log.debug("Customer get all recorded"); List<Customer>
	 * customerData=customServiveImpl.getAllCustomer();
	 * customerData.forEach(data->System.out.println(data.toString()));
	 * System.out.println("Customer Rest controller Ended"); return customerData; }
	 */

	@GetMapping(value = "/CustomersWebClient", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllWebClient() {
		System.out.println("Customer Web controller called");

		Flux<Customer> customerData = WebClient.create().get().uri("http://localhost:8085/api/CustomerData").retrieve()
				.bodyToFlux(Customer.class);
		customerData.subscribe(data -> System.out.println(data.toString()));
		System.out.println("Customer Web controller Ended");
		return customerData;
	}

	/**
	 * Method to update Customer by EmailId.
	 * 
	 * @param emailId
	 * @param customEmailId
	 * @return
	 */
	@GetMapping("/Customer/email/{emailId}")
	public List<Customer> getCustomByEmailId(@PathVariable(value = "emailId") String customEmailId) {
		System.out.println("Entered");
		log.debug("Customer Record taken by EmailId");
		List<Customer> customer = customServiveImpl.getByEmailId(customEmailId);
		// return customServiveImpl.getByEmailId(customEmailId);
		return customer;
	}

	/**
	 * Method to update Customer by City.
	 * 
	 * @param City
	 * @param cutomCity
	 * @return
	 */
	@GetMapping("/Customer/city/{city}")
	public List<Customer> getCustomByCity(@PathVariable(value = "city") String cutomCity) {
		log.debug("Customer Record taken by City");
		List<Customer> customer = customServiveImpl.getByCity(cutomCity);
		return customer;
	}

	/**
	 * Method to delete all Customer from the db.
	 * 
	 * @return
	 */
	@DeleteMapping("/Customers")
	public String deleteAll() {
		log.debug("All Customers are Deleted Successfully");
		customServiveImpl.deleteAllCustomers();
		return "All employee records deleted.";
	}

	/**
	 * Method to delete all Customer from the db.
	 * 
	 * @param customId
	 * @return
	 */

	@DeleteMapping("/Customer/{id}")
	public String deleteCustomerId(@PathVariable(value = "id") long customId) {
		log.debug("Customers Deleted by ID");
		customServiveImpl.deleteByCustomerId(customId);
		return "Customer record for Customer-id= " + customId + " deleted.";
	}

	/**
	 * Method to Update name Customer from the db.
	 * 
	 * @param name
	 * @return
	 */
	@PutMapping("/Customer/name/{name}")
	public Customer updateCustomerName(@PathVariable("name") String name,@RequestBody Customer customer) {
		log.debug("");
		System.out.println("Entered");
		// customerRest.setName(customer.name);
		Customer customerRest = customServiveImpl.findByCustomerName(name,customer);
		System.out.println(customerRest);
		return customerRest;
	}

	/**
	 * Method to Update name Customer from the db.
	 * 
	 * @param name
	 * @return
	 */
	@PutMapping("/Customer/id/{id}")
	public Customer updateCustomerId(@PathVariable("id") int id, @RequestBody String name) {
		log.debug("");
		System.out.println("Entered");
		Customer customerRest = customServiveImpl.findByCustomerId(id, name);
		System.out.println(customerRest);
		return customerRest;
		// Customer customerSet = customServiveImpl.createCustomer(name);
		// return "updated Sucessfully";
	}

	@Autowired
	private ImageService imageService;

	@PostMapping("/photos/add")
	public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model)
			throws IOException {
		String id = imageService.addPhoto(title, image);
		return "redirect:/photos/" + id;

	}

	@GetMapping("/photos/{id}")
	public String getPhoto(@PathVariable String id, Model model) {
		Image image = imageService.getPhoto(id);
		model.addAttribute("title", image.getTitle());
		model.addAttribute("image", Base64.getEncoder().encodeToString(image.getImage().getData()));
		return "photos";
	}
	
	@GetMapping("/CustomersBlocking")
	public List<Customer> getAllRestData() {
		System.out.println("Customer Rest controller called");
	
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<List<Customer>> response=restTemplate.exchange("http://localhost:8085/api/CustomerData",HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {});
		List<Customer> customerData=response.getBody();
		customerData.forEach(data->System.out.println(data.toString()));
		System.out.println("Customer Rest controller Ended");
		return customerData;
	}
	
	@PutMapping(value="/CustomersWeb/{name}")
	public Mono<Customer> getAllWeb(@PathVariable("name") String name,@RequestBody Customer customer) {
		System.out.println("Customer Web controller called");
		Mono<Customer> customerData=WebClient.create().put()
                .uri("http://localhost:8085/api/Customer/name/"+name,customer)
                .retrieve()
                .bodyToMono(Customer.class);
		System.out.println(customerData.toString());
		System.out.println("Customer Web controller Ended");
		return customerData;
	}

}
