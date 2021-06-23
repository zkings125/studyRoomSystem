package com.h.ssRoom.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//预约订单
@Entity
public class Appoints {
	//预约订单主键，不能为空，自增
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appointID;
	
	//顾客账号，外键，不能为空
	@NotNull
	@Column(length = 32)
	private String customerID;
	
	//自习室编号，外键，不能为空
	@NotNull
	private Integer ssRoomID;

	//座位编号，外键，不能为空
	@NotNull
	private Integer seatID;

	//预约开始时间
	private Timestamp startTime;

	//学习时长，以分为单位
	private Integer studyLen;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Integer getSsRoomID() {
		return ssRoomID;
	}

	public void setSsRoomID(Integer ssRoomID) {
		this.ssRoomID = ssRoomID;
	}

	public Integer getSeatID() {
		return seatID;
	}

	public void setSeatID(Integer seatID) {
		this.seatID = seatID;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Integer getStudyLen() {
		return studyLen;
	}

	public void setStudyLen(Integer studyLen) {
		this.studyLen = studyLen;
	}

	

	//	方法判断传入开始与结束时间是否与本订单冲突
	public String isCollision(String begin,String end){

		Date beginT = new Date();
		Date endT = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			beginT = sdf.parse(begin);
			endT = sdf.parse(end);
			System.out.println(beginT.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(this.startTime.getTime() + "-this.startTime.getTime()");
		System.out.println(beginT.getTime() + "-beginT.getTime()");
		System.out.println(this.startTime.getTime() + this.studyLen * 60000 + "-endTime");
		System.out.println(endT.getTime()+ "endT.getTime()");

		if(this.startTime.getTime() < beginT.getTime() && this.startTime.getTime() + this.studyLen * 60000 > beginT.getTime()){
			return "开始时间：" + this.startTime + "，预约时间："+ this.studyLen + "min";
		}
		if(this.startTime.getTime() < endT.getTime() && this.startTime.getTime() + this.studyLen * 60000 > endT.getTime()){
			return "开始时间：" + this.startTime + "，预约时间："+ this.studyLen + "min";
		}
		return "No";
	}

	public Integer getAppointID() {
		return appointID;
	}

	public void setAppointID(Integer appointID) {
		this.appointID = appointID;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

}
