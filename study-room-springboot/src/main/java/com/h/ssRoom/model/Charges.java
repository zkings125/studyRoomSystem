package com.h.ssRoom.model;

import java.sql.Date;

import javax.persistence.Entity;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//充值记录
@Entity
public class Charges {
	//充值记录主键，不能为空，自增
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cusID;
	
	//顾客账号，不能为空
	@NotNull
	@Column(length = 32)
	private String customerID;
	
	//充值时间
	private Timestamp time;
	
	//充值时长，只能充10的整数倍，以小时为单位
	private Integer length;

	public String getCustomerID() {
		return customerID;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
}
