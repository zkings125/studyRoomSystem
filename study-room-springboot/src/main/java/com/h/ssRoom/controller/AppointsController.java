package com.h.ssRoom.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.h.ssRoom.model.Appoints;
import com.h.ssRoom.repo.AppointsRepo;

@RestController
@RequestMapping(path = "/api", produces = "application/json;charset=utf-8")
public class AppointsController {
	@Autowired
	private AppointsRepo appointsRepo;

	// 获取预约记录
	@PostMapping(value = "/appointRecord")
	public List<Map<String,Object>> getAppoints(@RequestParam(value = "customerID", required = true) String customerID) {
		List<Map<String,Object>> result = appointsRepo.getAppointsByID(customerID);
		System.out.println();
		return result;
	}

	// 新增预约记录
	// 新增预约记录
	@PostMapping(value = "/AddRecord")
	public Map<String, Object> insertAppoints(@RequestParam(value = "customerID", required = true) String customerID,
			@RequestParam(value = "ssRoomID", required = true) Integer ssRoomID,
			@RequestParam(value = "seatID", required = true) Integer seatID,
			@RequestParam(value = "startTime", required = true) String startTime,
			@RequestParam(value = "studyLen", required = true) Integer studyLen) {
		// String datestr = "2015-07-08 11:32:21";
		// Timestamp ts = Timestamp.valueOf(datestr);
		// System.out.println(ts);
		// String转Timestamp
		Appoints appoints = new Appoints();
		appoints.setCustomerID(customerID);
		appoints.setSeatID(seatID);
		appoints.setSsRoomID(ssRoomID);
		appoints.setStartTime(Timestamp.valueOf(startTime));
		appoints.setStudyLen(studyLen);
		
		System.out.println(appointsRepo.save(appoints) + "save");
		// Timestamp ts = Timestamp.valueOf(startTime);
		// System.out.println("进入增加记录");
		// System.out.println(ts);
		// System.out.println("新增预约记录");
		Map<String, Object> result = new HashMap<>();
		// appointsRepo.insertAppointsByID(customerID, ssRoomID, seatID, ts, studyLen);
		// System.out.println("插入预约记录成功" + ts);
		result.put("status", "插入一条预约记录成功");
		result.put("stateCode", 137);
		return result;
	}

	// @PostMapping(value="/AddRecord")
	// public Map<String, Object>
	// insertAppoints(@RequestParam(value="customerID",required=true) String
	// customerID,
	// @RequestParam(value="ssRoomID",required=true) Integer ssRoomID,
	// @RequestParam(value="seatID",required=true) Integer seatID,
	// @RequestParam(value="startTime",required=true) String startTime,
	// @RequestParam(value="studyLen",required=true) Integer studyLen) {
	// //String转Timestamp
	// Timestamp ts = Timestamp.valueOf(startTime);
	// System.out.println("新增预约记录");
	// Map<String,Object> result = new HashMap<>();
	// appointsRepo.insertAppointsByID(customerID, ssRoomID, seatID, ts, studyLen);
	// System.out.println("0000000000000000000000000000000000000000");
	// result.put("status","success");
	// List<Appoints> appointList=appointsRepo.getAppointsByID(customerID);
	// if(appointList.size()==0) {
	// result.put("status", "插入失败");
	// return result;
	// } else {
	// result.put("status", "插入成功");
	// return result;
	// }
	// }

	// 学习记录
	// @GetMapping("/study_record")
	// public List<Appoints> studyRecord(@RequestParam(value = "customerID",required
	// = true) String customerID){
	// List<Appoints> appointsList = appointsRepo.studyRecord(customerID);
	// return appointsList;
	// }
}
