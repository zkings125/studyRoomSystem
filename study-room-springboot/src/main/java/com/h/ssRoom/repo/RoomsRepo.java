package com.h.ssRoom.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.h.ssRoom.model.Rooms;

//用来操作数据库的
public interface RoomsRepo extends JpaRepository<Rooms, Integer> {
    //获取自习室信息
    @Query(value = "select * from rooms", nativeQuery = true)
    public List<Rooms> getRoomInfo();

    //根据自习室编号获取自习室
    @Query(value = "select * from rooms where ss_roomid=?1",nativeQuery = true)
    public List<Rooms> getRoomsById(Integer ssRoomID);
    
    //修改自习室信息
    @Transactional
    @Modifying
    @Query(value = "update rooms set ss_roomid=?1,ss_room_type=?2,ss_room_name=?3,introduce=?4,cover_map=?5," +
            "address=?6,loc_num=?7,use_num=?8 where ss_roomid=?9",nativeQuery = true)
    public void updateRooms(Integer ssRoomID,Integer ssRoomType,String ssRoomName,String introduce,
                                String coverMap,String address,Integer locNum, Integer useNum,Integer oldssRoomID);
    
    //新增自习室
    @Transactional
    @Modifying
    @Query(value = "insert into rooms (ss_roomid,ss_room_type,ss_room_name,introduce,cover_map,address,loc_num,use_num)"+
    "values (?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    public void addRooms(Integer ssRoomID,Integer ssRoomType,String ssRoomName,String introduce,
                                String coverMap,String address,Integer locNum, Integer useNum);
    
    //根据自习室编号删除自习室
    @Transactional
    @Modifying
    @Query(value = "delete from rooms where ss_roomid=?1",nativeQuery = true)
    public void deleteRooms(Integer ssRoomID);
}
