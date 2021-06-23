package com.h.ssRoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h.ssRoom.model.Admins;
import com.h.ssRoom.repo.AdminsRepo;

@SpringBootTest
public class AdminsTests {
	@Autowired
	private AdminsRepo adminRepo;
	
	@Test
	void testAdminss() {
		adminRepo.deleteAll();
		Admins admin1=new Admins();
		admin1.setAdminID("111");
		admin1.setAdminPwd("112111e");
		admin1.setAdminPower(1);
		adminRepo.save(admin1);
		
		Admins admin2=new Admins();
		admin2.setAdminID("11122");
		admin2.setAdminPwd("1121e");
		admin2.setAdminPower(1);
		adminRepo.save(admin2);
	}
}
