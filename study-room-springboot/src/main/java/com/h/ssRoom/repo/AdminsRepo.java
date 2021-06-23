package com.h.ssRoom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.h.ssRoom.model.Admins;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminsRepo extends JpaRepository<Admins,Integer> {
    //查询管理员
    @Query(value = "select * from admins where adminid=?1",nativeQuery = true)
    public Admins findByAdminID(String adminID);

    //查询管理员
    @Query(value = "select * from admins where adminid=?1",nativeQuery = true)
    public List<Admins> findByadminID(String adminID);
    
    //查询所有管理员
    @Query(value = "select * from Admins",nativeQuery = true)
    public List<Admins> findAllAdmins();
    
    //修改管理员信息
    @Transactional
    @Modifying
    @Query(value = "update admins set adminid=?1,admin_pwd=?2,admin_power=?3 where adminid=?4",nativeQuery = true)
    public void updateAdmins(String adminID,String adminPwd,Integer adminPower,String oldAdminID);

    
    //删除管理员
    @Transactional
    @Modifying
    @Query(value = "delete  from admins where adminID=?1",nativeQuery = true)
    public void deleteAdmins(String adminID);

    //修改密码
    @Transactional
    @Modifying
    @Query(value = "update admins set admin_pwd=?1 where adminid=?2",nativeQuery = true)
    public void updatePassword(String adminPwd,String adminID);
}
