package com.h.ssRoom.repo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.h.ssRoom.model.Appoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppointsRepo extends JpaRepository<Appoints, Integer>{
    //根据顾客编号查询预约订单
    @Query(value = "SELECT appointid,customerid,seatid,ss_roomid,DATE_FORMAT(start_time,'%Y-%m-%d %H:%i') AS start_time,study_len FROM appoints WHERE seatid <> 0 AND ss_roomid <> 0 AND customerid = ?1 ORDER BY start_time DESC", nativeQuery = true)
    public List<Map<String,Object>> getAppointsByID(String customerID);
    
    //根据顾客编号插入预约订单
    @Transactional
    @Modifying
    @Query(value = "insert into appoints(customerid,ss_roomid,seatid,start_time,study_len) values(?1,?2,?3,?4,?5);", nativeQuery = true)
    public List<Appoints> insertAppointsByID(String customerID,Integer ssRoomID,Integer seatID,Timestamp startTime,Integer studyLen); 

    
    //根据用户编号获取学习记录
    @Query(value = "select * from appoints where customerid = ?1",nativeQuery = true)
    public List<Appoints> studyRecord(String customerID);

    //根据自习室座位编号查找
    @Query(value = "select * from appoints where ss_roomid = ?1 and seatid = ?2",nativeQuery = true)
    public List<Appoints> getAppointsByssRoomIDAndseatID(Integer ssRoomID,Integer seatID);
}
