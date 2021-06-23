package com.h.ssRoom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.h.ssRoom.model.Seats;

public interface SeatsRepo extends JpaRepository<Seats, Integer> {

    // 根据自习室查询
    @Query(value = "select * from seats where ss_roomid = ?1", nativeQuery = true)
    public List<Seats> getSeatsByRoom(String RoomID);
}
