package com.brainmentors.los.operations;

import java.util.ArrayList;

import com.brainmentors.los.customer.Customer;
import com.brainmentors.los.customer.PersonalInformation;

public interface DB {
public static ArrayList<Customer> getNegetiveCustomers() {
	
	ArrayList <Customer> negetiveCustomers = new ArrayList<>();
	Customer customer = new Customer(); //Fake DB about a Thief customer inside a Customer obj
	customer.setId(1010);
	PersonalInformation pd=new PersonalInformation();
	pd.setFirstName("Tim");
	pd.setLastName("Jackson");
	pd.setPhone("2222");
	pd.setPanCard("pw1000");
	pd.setVoterId("A1111");
	pd.setEmail("Blah@blah.com");
	pd.setAge(24);
	customer.setPersonal(pd);
	negetiveCustomers.add(customer);
	
	return negetiveCustomers;
	
}
}
