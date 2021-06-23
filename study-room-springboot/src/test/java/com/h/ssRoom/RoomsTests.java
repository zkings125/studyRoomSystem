package com.h.ssRoom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.h.ssRoom.model.Rooms;
import com.h.ssRoom.repo.RoomsRepo;

@SpringBootTest
public class RoomsTests {
    @Autowired
    private RoomsRepo roomsRepo;

    @Test
    void testRoom(){
        roomsRepo.deleteAll();
        Rooms room1 = new Rooms();
        room1.setSsRoomID(5);
        room1.setSsRoomName("西柚自习室");
        room1.setAddress("四川省成都市武侯区望江路1号中海广场1106室");
        room1.setCoverMap("https://study-room.obs.cn-north-4.myhuaweicloud.com/library_1.jpg");
        room1.setLocNum(50);
        room1.setUseNum(35);
        roomsRepo.save(room1);

        Rooms room2 = new Rooms();
        room2.setSsRoomID(2);
        room2.setSsRoomName("省图书馆自习室");
        room2.setAddress("四川省成都市青羊区人民西路4号天府广场旁");
        room2.setCoverMap("https://study-room.obs.cn-north-4.myhuaweicloud.com/library_2.jpg");
        room2.setLocNum(45);
        room2.setUseNum(9);
        roomsRepo.save(room2);

        Rooms room3 = new Rooms();
        room3.setSsRoomID(20);
        room3.setSsRoomName("K书吧自习空间");
        room3.setAddress("四川省成都市青羊区光华中心C座407号");
        room3.setCoverMap("https://study-room.obs.cn-north-4.myhuaweicloud.com/library_3.jpg");
        room3.setLocNum(130);
        room3.setUseNum(55);
        roomsRepo.save(room3);
        

        Rooms room4 = new Rooms();
        room4.setSsRoomID(12);
        room4.setSsRoomName("星梦书屋（师大店）");
        room4.setAddress("四川省成都市龙泉驿区成龙大道二段1819号四川师范大学成龙校区图书馆三楼");
        room4.setCoverMap("https://study-room.obs.cn-north-4.myhuaweicloud.com/library_4.jpg");
        room4.setLocNum(6);
        room4.setUseNum(6);
        roomsRepo.save(room4);
    }
}
