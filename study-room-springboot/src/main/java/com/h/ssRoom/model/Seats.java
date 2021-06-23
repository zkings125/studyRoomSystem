package com.h.ssRoom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

//座位表实体类
@IdClass(RelationPK.class)
@Entity
public class Seats {
	//座位编号，主键，不能为空
	@Id
//	@Column(name = "seat_id")
	@NotNull
	private Integer seatID;
	
	//自习室编号，主键，外键，不能为空
	@Id
//	@Column(name = "ssRoom_id")
	@NotNull
	private Integer ssRoomID;
	
	//座位类型，预留字段
	private Integer seatType;
	
	//座位状态，不能为空，默认为0，0代表可用，1代表不可用
	private Integer seatStatus=0;

	public Integer getSeatID() {
		return seatID;
	}

	public void setSeatID(Integer seatID) {
		this.seatID = seatID;
	}

	public Integer getSsRoomID() {
		return ssRoomID;
	}

	public void setSsRoomID(Integer ssRoomID) {
		this.ssRoomID = ssRoomID;
	}

	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	public Integer getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(Integer seatStatus) {
		this.seatStatus = seatStatus;
	}
}
