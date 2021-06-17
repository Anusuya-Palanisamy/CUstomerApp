package com.aspire.CustomerApp.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Document(collection = "Customer")
public class Customer {
	/*
	 * Data declared
	 */
	@Transient
    public static final String SEQUENCE_NAME = "customerSeq";
	
	@Id
	public long id;

	@NotEmpty(message = "Name is compulsory")
	public String name;

	@NotEmpty(message = "Type is compulsory")
	public String type;

	@NotEmpty(message = "EmailId is compulsory")
	public String emailId;

	public Address address;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(long id, String name, String type, String emailId, Address string) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.emailId = emailId;
		this.address = string;
	}
	/*
	 * Implement Getter and Setter
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}
