package com.h.ssRoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h.ssRoom.model.Customers;
import com.h.ssRoom.repo.CustomersRepo;

@SpringBootTest
public class CustomersTests {
	@Autowired
	private CustomersRepo customersRepo;
	
	@Test
	void testGetMemLength() {
		customersRepo.deleteAll();
		Customers customer1=new Customers();
		customer1.setCustomerID("1");
		customersRepo.save(customer1);

		
		Customers customer2=new Customers();
		customer2.setCustomerID("2");
		customersRepo.save(customer2);
		
		Customers customer3=new Customers();
		customer3.setCustomerID("3");
		customer3.setMemLength(20);
		customersRepo.save(customer3);
	}
}
