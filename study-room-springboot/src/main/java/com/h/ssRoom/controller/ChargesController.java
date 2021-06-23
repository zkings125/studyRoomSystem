package com.h.ssRoom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h.ssRoom.model.Charges;
import com.h.ssRoom.repo.ChargesRepo;
import com.h.ssRoom.repo.CustomersRepo;

@RestController
@RequestMapping(path="/api",produces="application/json;charset=utf-8")
public class ChargesController {
	@Autowired
	private CustomersRepo customersRepo;
	
	//获取时长
	@GetMapping(value="/getTimeLen")
	public Integer getTimeLen(@RequestParam(value="customerID",required=true) String customerID) {
		System.out.println("获取时长成功");
		Integer result=customersRepo.getMemLenByID(customerID);
		return result;
	}
	
	@Autowired
	private ChargesRepo chargesRepo;
	
	//获取充值记录
	@PostMapping(value="/recharge")
	public List<Charges> getCharges(@RequestParam(value="customerID",required=true) String customerID) {
		System.out.println("获取充值记录成功");
		List<Charges> result=chargesRepo.getChargesByID(customerID);
		for(Charges str : result) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
		    System.out.println(str.getTime());
		}
		System.out.println(result);
		return result;
	}
	
	//插入充值记录
	@PostMapping(value="/insertRecharge")
	public Map<String,Object> insertCharges(@RequestParam(value="customerID",required=true) String customerID,
			@RequestParam(value="time",required=true) String time,
			@RequestParam(value="length",required=true) Integer length
			) {
		Map<String,Object> result = new HashMap<>();
		//String转Timestamp
		Timestamp ts = Timestamp.valueOf(time);
		chargesRepo.insertCharge(customerID, ts, length);
		System.out.println("插入充值记录成功"+ts);
		result.put("status","插入一条充值记录成功");
		result.put("stateCode", 137);
		return result;
	}
	
	//充值，返回0表示充值失败，返回1表示充值成功
	@PostMapping(value="/chargeTime")
	public Integer chargeTime(@RequestParam(value="length",required=true) Integer length,
	@RequestParam(value="customerID",required=true) String customerID) {
		Integer result=chargesRepo.updateMemLen(length, customerID);
		return result;
	}
}
