package com.h.ssRoom.repo;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.h.ssRoom.model.Charges;

public interface ChargesRepo extends JpaRepository<Charges, Integer>{
	//根据顾客编号查询充值记录
    @Query(value = "select * from charges where customerID=?1 order by time desc", nativeQuery = true)
    public List<Charges> getChargesByID(String customerID);
    
	//插入一条充值记录
    @Transactional
    @Modifying
    @Query(value = "insert into charges(customerid,time,length) values(?1,?2,?3)", nativeQuery = true)
    public void insertCharge(String customerID,Timestamp time,Integer length);
    
    //充值：根据顾客编号修改剩余时长
    @Transactional
    @Modifying
    @Query(value = "update customers set mem_length=mem_length+?1 where customerID=?2",nativeQuery = true)
    public Integer updateMemLen(Integer length,String customerID);
    
}
