package com.h.ssRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.h.ssRoom.model.Admins;
import com.h.ssRoom.repo.AdminsRepo;
import com.h.ssRoom.repo.AppointsRepo;
import com.h.ssRoom.repo.ChargesRepo;
import com.h.ssRoom.repo.CustomersRepo;
import com.h.ssRoom.repo.RoomsRepo;

@SpringBootApplication
public class SsRoomApplication {
    @Autowired
    private AppointsRepo appointsRepo;
    
    @Autowired
    private ChargesRepo chargesRepo;
    
    @Autowired
    private CustomersRepo customersRepo;
    
    @Autowired
    private RoomsRepo roomsRepo;
    
    @Autowired
    private AdminsRepo adminsRepo;
    
	public static void main(String[] args) {
		SpringApplication.run(SsRoomApplication.class, args);
	}

}
