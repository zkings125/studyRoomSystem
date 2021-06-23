package com.h.ssRoom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//自习室实体类
@Entity
public class Rooms {
	//自习室编号，主键，不能为空
	@Id
	@NotNull
	private Integer ssRoomID;
	
	//自习室类型，预留字段，作为自习室是否停用的字段，0表示停用，1表示正常使用
	private Integer ssRoomType;
	
	//自习室名称，不能为空
	@NotNull
	@Column(length = 64)
	private String ssRoomName;
	
	//自习室封面图
	@Column(length = 1024)
	private String coverMap;
	
	//自习室介绍
	@Column(length = 1024)
	private String introduce;
	
	//自习室地址，不能为空
	@NotNull
	@Column(length = 1024)
	private String address;
	
	//自习室座位总数量，不能为空，默认值为0
	@NotNull
	private Integer locNum=0;
	
	//自习室座位使用数量，不能为空，默认值为0
	@NotNull
	private Integer useNum=0;

	public Integer getSsRoomID() {
		return ssRoomID;
	}

	public void setSsRoomID(Integer ssRoomID) {
		this.ssRoomID = ssRoomID;
	}

	public Integer getSsRoomType() {
		return ssRoomType;
	}

	public void setSsRoomType(Integer ssRoomType) {
		this.ssRoomType = ssRoomType;
	}

	public String getSsRoomName() {
		return ssRoomName;
	}

	public void setSsRoomName(String ssRoomName) {
		this.ssRoomName = ssRoomName;
	}

	public String getCoverMap() {
		return coverMap;
	}

	public void setCoverMap(String coverMap) {
		this.coverMap = coverMap;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLocNum() {
		return locNum;
	}

	public void setLocNum(Integer locNum) {
		this.locNum = locNum;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
}
