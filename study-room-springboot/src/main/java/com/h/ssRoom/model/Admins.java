package com.h.ssRoom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//管理员实体类
@Entity
public class Admins {
    //管理员账号，主键，不能为空
	@Id
    @NotNull
    @Column(length = 32)
    private String adminID;
    
	//管理员密码 ，不能为空
    @NotNull
    @Column(length = 32)
    private String adminPwd;
    
    //管理员权限，0代表超级管理员，1代表普通管理员
    @NotNull
    private Integer adminPower;

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public Integer getAdminPower() {
		return adminPower;
	}

	public void setAdminPower(Integer adminPower) {
		this.adminPower = adminPower;
	}
}
