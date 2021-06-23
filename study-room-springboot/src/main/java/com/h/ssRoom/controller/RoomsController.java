package com.h.ssRoom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.h.ssRoom.model.Rooms;
import com.h.ssRoom.repo.RoomsRepo;
import com.h.ssRoom.util.AliyunOssHelper;

@RestController
@CrossOrigin
@RequestMapping(path = "/api",produces = "application/json; charset=utf-8")
public class RoomsController {
    @Autowired
    private RoomsRepo roomsRepo;
    
    //获取自习室信息
    @CrossOrigin
    @GetMapping(value = "/getRoom")
    public List<Rooms> getRooms() {
        return roomsRepo.getRoomInfo();
    }
    
    //根据自习室编号查询自习室信息
    @PostMapping(value = "/rooms")
    public List<Rooms> getRooms(@RequestParam(value ="ssRoomID") Integer ssRoomID){
        List<Rooms> result = roomsRepo.getRoomsById(ssRoomID);
        return  result;
    }
    
    //修改自习室信息
    @PostMapping(value = "/change_roomsinfo")
    public Map<String,Object> changeRooms(@RequestParam(value = "ssRoomID") Integer ssRoomID,
                                             @RequestParam(value = "ssRoomType" ) Integer ssRoomType,
                                             @RequestParam(value = "ssRoomName") String ssRoomName,
                                             @RequestParam(value = "introduce") String introduce,
                                             @RequestParam(value = "coverMap") String coverMap,
                                             @RequestParam(value = "address") String address,
                                             @RequestParam(value = "locNum") Integer locNum,
                                             @RequestParam(value = "useNum") Integer useNum,
                                             @RequestParam(value = "oldssRoomID") Integer oldssRoomID){
        Map<String, Object> result = new HashMap<>();
        List<Rooms> roomsList = roomsRepo.getRoomsById(oldssRoomID);
        if (roomsList.size() == 0) {
            result.put("faild", "自习室不存在");
            return result;
        }else{
            roomsRepo.updateRooms(ssRoomID,ssRoomType,ssRoomName,introduce,coverMap,address,locNum,useNum,oldssRoomID);
//            result.put("aa",roomsList.get(0));
            result.put("status", "修改自习室信息成功");
            result.put("aa",roomsList.get(0));
            return result;
        }
    }
    
    //新增自习室
    @PostMapping(value = "/create_rooms")
    public Map<String,Object> createRooms(@RequestParam(value = "ssRoomID") Integer ssRoomID,
                                           @RequestParam(value = "ssRoomType" ) Integer ssRoomType,
                                           @RequestParam(value = "ssRoomName") String ssRoomName,
                                           @RequestParam(value = "introduce") String introduce,
                                           @RequestParam(value = "coverMap") String coverMap,
                                           @RequestParam(value = "address") String address,
                                           @RequestParam(value = "locNum") Integer locNum,
                                           @RequestParam(value = "useNum") Integer useNum){
        Map<String,Object> result = new HashMap<>();
        List<Rooms> roomsList = roomsRepo.getRoomsById(ssRoomID);
        if(roomsList.size()==0) {
        	roomsRepo.addRooms(ssRoomID, ssRoomType, ssRoomName, introduce, coverMap, address, locNum, useNum);
            result.put("status", "创建成功");
            return result;
        }else{
            result.put("status","创建失败，自习室编号已存在");
            return result;
        }
    }
    
    //删除自习室
    @PostMapping(value ="/delete_rooms")
    public Map<String,Object> deleteRooms(@RequestParam(value ="ssRoomID") Integer ssRoomID) {
        Map<String, Object> result = new HashMap<>();
        List<Rooms> roomsList = roomsRepo.getRoomsById(ssRoomID);
        if (roomsList.size() == 0) {
            result.put("faild", "自习室不存在");
            return result;
        } else {
            roomsRepo.deleteRooms(ssRoomID);
            result.put("status", "删除自习室成功");
            return result;
        }
    }


    @PostMapping(value = "/upload_image")
    public Map<String,Object> uploadImage(@RequestParam(value = "imageFile",required = true) MultipartFile imageFile){
        String imageUrl = AliyunOssHelper.uploadImage(imageFile);

        Map<String,Object> result = new HashMap<>();
        result.put("status","ok");
        result.put("imageUrl",imageUrl);
        return result;
    }
}
