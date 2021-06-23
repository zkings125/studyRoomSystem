package com.h.ssRoom.util;

import com.h.ssRoom.model.Seats;

public class SeatHelper {
    //座位编号
    private Integer seatID;
    //自习室编号
    private Integer ssRoomID;
    //座位类型
    private Integer seatType;
    //座位状态
    private Integer seatStatus;
    //座位可用Boolean
    private Boolean useful;
    //冲突时间
    private String useTime;

    public SeatHelper(Seats s) {
        this.seatID = s.getSeatID();
        this.ssRoomID = s.getSsRoomID();
        this.seatType = s.getSeatType();
        this.seatStatus = s.getSeatStatus();
        this.useful = true;
        this.useTime = "";
    }

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

    public Boolean getUseful() {
        return useful;
    }

    public void setUseful(Boolean useful) {
        this.useful = useful;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}
