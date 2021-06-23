package com.h.ssRoom.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//复合主键类
@SuppressWarnings("serial")
public class RelationPK implements Serializable {
	//座位编号，主键，不能为空
	@Id
	@NotNull
	private Integer seatID;
	
	//自习室编号，主键，外键，不能为空
	@Id
	@NotNull
	private Integer ssRoomID;
}
