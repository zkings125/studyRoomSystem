package com.h.ssRoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h.ssRoom.model.Charges;
import com.h.ssRoom.repo.ChargesRepo;

@SpringBootTest
public class ChargesTests {
	@Autowired
	private ChargesRepo chargesRepo;
	
	@Test
	void testCharges() {
		chargesRepo.deleteAll();
		Charges charge1=new Charges();
		charge1.setCustomerID("1");
		charge1.setLength(11);
		chargesRepo.save(charge1);
	}
}
