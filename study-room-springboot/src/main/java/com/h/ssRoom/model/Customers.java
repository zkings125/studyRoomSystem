package com.h.ssRoom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//顾客实体类
@Entity
public class Customers {
	//顾客账号，主键，不能为空
	@Id
	@NotNull
	@Column(length = 32)
	private String customerID;
	
	//顾客昵称
	@Column(length = 64)
	private String customerName;
	
	//会员时长，不能为空，默认值为0
	@NotNull
	private Integer memLength=0;
	
	//订座状态，不能为空，默认值为0，0代表目前没有订座，1代表目前有订单正在进行
	@NotNull
	private Integer resStatus=0;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getMemLength() {
		return memLength;
	}

	public void setMemLength(Integer memLength) {
		this.memLength = memLength;
	}

	public Integer getResStatus() {
		return resStatus;
	}

	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}
	
}
