package com.h.ssRoom.controller;


import com.h.ssRoom.model.Appoints;
import com.h.ssRoom.model.Seats;
import com.h.ssRoom.repo.AppointsRepo;
import com.h.ssRoom.repo.SeatsRepo;
import com.h.ssRoom.util.SeatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api",produces = "application/json; charset=utf-8")
public class SeatsController {
    @Autowired
    private SeatsRepo seatsRepo;
    @Autowired
    private AppointsRepo appointsRepo;

    @RequestMapping(value = "/getSeatsByTime")
    public List<SeatHelper> getSeatsByTime(@RequestParam(value = "ssRoomID",required = true) String ssRoomID,
                                           @RequestParam(value = "beginTime",required = true) String beginTime,
                                           @RequestParam(value = "endTime",required = true) String endTime){


        List<Seats> seats = seatsRepo.getSeatsByRoom(ssRoomID);
        List<SeatHelper> seatForTime = new ArrayList<>();

        //遍历seats传入seatHelper
        if(seats.size() == 0){
            return null;
        }else{
            for(Seats s : seats){
                SeatHelper seat = new SeatHelper(s);
                System.out.println(seat.getSeatID());
                seatForTime.add(seat);
            }
        }

        System.out.println(seatForTime);


        //遍历每一个座位
        if(seatForTime.size() == 0){
            return null;
        }else{
            for(SeatHelper s : seatForTime){
                //查询座位订单

                List<Appoints> appoints = appointsRepo.getAppointsByssRoomIDAndseatID(s.getSsRoomID(), s.getSeatID());

                //遍历每一个座位订单
                if(appoints.size() != 0){
                    System.out.println(appoints.size() + "size");

                    for(Appoints A : appoints){

                        //判断订单是否冲突
                        String isColl = A.isCollision(beginTime,endTime);
                        System.out.println(isColl);
                        //不冲突
                        if(!isColl.equals("No")){
                            //列出冲突时间
                          s.setUseful(false);
                          System.out.println(isColl + "oooooooooooooooooooooo");
                          s.setUseTime(s.getUseTime() == "" ? isColl:s.getUseTime() + "、" + isColl);
                        }
                    }
                }
            }

        }

        return seatForTime;

    }

}
