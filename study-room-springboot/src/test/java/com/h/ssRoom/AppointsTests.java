package com.h.ssRoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h.ssRoom.model.Appoints;
import com.h.ssRoom.repo.AppointsRepo;

@SpringBootTest
public class AppointsTests {
	@Autowired
	private AppointsRepo appointsRepo;
	
	@Test
	void testAppoints() {
		appointsRepo.deleteAll();
		Appoints appoint1=new Appoints();
		appoint1.setCustomerID("111");
		appoint1.setSsRoomID(1);
		appoint1.setSeatID(2);
		appointsRepo.save(appoint1);
	}
}
