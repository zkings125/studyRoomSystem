/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : ssroom

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 23/06/2021 09:37:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `adminid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_power` int(11) NOT NULL,
  `admin_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('111', 1, '112111e');
INSERT INTO `admins` VALUES ('11122', 1, '1121e');
INSERT INTO `admins` VALUES ('admin', 1, '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for appoints
-- ----------------------------
DROP TABLE IF EXISTS `appoints`;
CREATE TABLE `appoints`  (
  `appointid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `seatid` int(11) NOT NULL,
  `ss_roomid` int(11) NOT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `study_len` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`appointid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appoints
-- ----------------------------
INSERT INTO `appoints` VALUES (24, '111', 2, 1, '2021-06-21 23:52:53', 120);
INSERT INTO `appoints` VALUES (25, '112', 1, 1, '2021-06-21 19:43:23', 200);
INSERT INTO `appoints` VALUES (26, '112', 2, 1, '2021-06-21 22:43:55', 120);
INSERT INTO `appoints` VALUES (27, '111', 3, 5, '2021-06-21 10:03:04', 30);
INSERT INTO `appoints` VALUES (29, '111', 3, 5, '2021-06-20 15:04:19', 120);
INSERT INTO `appoints` VALUES (30, '111', 3, 5, '2021-06-20 07:04:55', 30);
INSERT INTO `appoints` VALUES (31, '111', 3, 5, '2021-06-19 16:06:43', 53);
INSERT INTO `appoints` VALUES (32, '111', 2, 4, '2021-06-18 20:07:16', 29);
INSERT INTO `appoints` VALUES (33, '111', 1, 2, '2021-06-17 09:08:06', 23);
INSERT INTO `appoints` VALUES (34, '111', 1, 2, '2021-06-16 12:20:48', 59);
INSERT INTO `appoints` VALUES (35, '111', 3, 12, '2021-06-15 14:56:41', 37);
INSERT INTO `appoints` VALUES (36, '111', 1, 7, '2021-06-14 03:23:31', 11);
INSERT INTO `appoints` VALUES (37, '111', 1, 7, '2021-06-14 03:20:05', 3);
INSERT INTO `appoints` VALUES (38, '111', 4, 2, '2021-06-13 20:13:37', 131);
INSERT INTO `appoints` VALUES (39, '111', 2, 3, '2021-06-22 00:30:22', 13);
INSERT INTO `appoints` VALUES (40, '112', 3, 5, '2021-06-18 13:49:25', 13);
INSERT INTO `appoints` VALUES (41, '1', 1, 1, '2021-06-22 16:21:45', 50);
INSERT INTO `appoints` VALUES (42, 'b155877b52fb13d156512972a56bc05d', 3, 1, '2021-06-22 13:21:45', 3);
INSERT INTO `appoints` VALUES (43, 'b155877b52fb13d156512972a56bc05d', 2, 2, '2021-06-22 13:37:30', 2);
INSERT INTO `appoints` VALUES (44, 'b155877b52fb13d156512972a56bc05d', 2, 5, '2021-06-22 13:38:26', 2);
INSERT INTO `appoints` VALUES (45, 'b155877b52fb13d156512972a56bc05d', 3, 5, '2021-06-22 13:39:20', 3);
INSERT INTO `appoints` VALUES (46, 'b155877b52fb13d156512972a56bc05d', 3, 5, '2021-06-22 13:39:20', 3);
INSERT INTO `appoints` VALUES (47, 'b155877b52fb13d156512972a56bc05d', 2, 5, '2021-06-22 13:40:37', 3);
INSERT INTO `appoints` VALUES (48, 'b155877b52fb13d156512972a56bc05d', 2, 5, '2021-06-22 13:41:37', 4);
INSERT INTO `appoints` VALUES (49, 'b155877b52fb13d156512972a56bc05d', 2, 5, '2021-06-22 13:42:20', 3);
INSERT INTO `appoints` VALUES (50, 'b155877b52fb13d156512972a56bc05d', 2, 5, '2021-06-22 13:44:12', 3);
INSERT INTO `appoints` VALUES (60, '1', 1, 1, '2021-06-22 19:53:05', 50);
INSERT INTO `appoints` VALUES (61, '089fd00ba1ac4429b697ae78199f33c8', 2, 2, '2021-06-22 22:57:42', 10);
INSERT INTO `appoints` VALUES (62, '089fd00ba1ac4429b697ae78199f33c8', 2, 2, '2021-06-22 22:59:45', 10);
INSERT INTO `appoints` VALUES (63, '1', 1, 1, '2021-06-22 19:53:05', 50);
INSERT INTO `appoints` VALUES (64, '1', 1, 1, '2021-06-22 19:53:05', 50);
INSERT INTO `appoints` VALUES (65, '1', 1, 1, '2021-06-22 22:57:42', 60);
INSERT INTO `appoints` VALUES (66, '1', 1, 1, '2021-06-22 19:53:05', 50);
INSERT INTO `appoints` VALUES (67, '089fd00ba1ac4429b697ae78199f33c8', 2, 2, '2021-06-21 00:09:38', 30);
INSERT INTO `appoints` VALUES (68, '089fd00ba1ac4429b697ae78199f33c8', 3, 2, '2021-06-22 00:11:30', 30);
INSERT INTO `appoints` VALUES (69, '089fd00ba1ac4429b697ae78199f33c8', 2, 2, '2021-06-15 00:11:45', 30);
INSERT INTO `appoints` VALUES (70, '089fd00ba1ac4429b697ae78199f33c8', 2, 2, '2021-06-16 00:14:30', 180);
INSERT INTO `appoints` VALUES (71, '089fd00ba1ac4429b697ae78199f33c8', 2, 1, '2021-06-17 00:21:10', 180);
INSERT INTO `appoints` VALUES (72, '089fd00ba1ac4429b697ae78199f33c8', 14, 2, '2021-06-18 00:36:12', 180);
INSERT INTO `appoints` VALUES (73, '089fd00ba1ac4429b697ae78199f33c8', 14, 2, '2021-06-18 04:37:04', 180);
INSERT INTO `appoints` VALUES (74, '089fd00ba1ac4429b697ae78199f33c8', 46, 2, '2021-06-19 08:17:55', 90);
INSERT INTO `appoints` VALUES (75, '089fd00ba1ac4429b697ae78199f33c8', 30, 2, '2021-06-20 08:20:52', 60);
INSERT INTO `appoints` VALUES (77, '089fd00ba1ac4429b697ae78199f33c8', 22, 2, '2021-06-21 08:22:09', 90);
INSERT INTO `appoints` VALUES (78, '089fd00ba1ac4429b697ae78199f33c8', 14, 2, '2021-06-22 08:55:17', 90);
INSERT INTO `appoints` VALUES (79, '089fd00ba1ac4429b697ae78199f33c8', 15, 2, '2021-06-20 09:01:47', 50);
INSERT INTO `appoints` VALUES (80, '089fd00ba1ac4429b697ae78199f33c8', 8, 2, '2021-06-19 09:04:03', 70);
INSERT INTO `appoints` VALUES (81, '089fd00ba1ac4429b697ae78199f33c8', 7, 2, '2021-06-16 09:04:17', 40);
INSERT INTO `appoints` VALUES (82, '089fd00ba1ac4429b697ae78199f33c8', 5, 2, '2021-06-17 09:04:43', 40);
INSERT INTO `appoints` VALUES (83, '089fd00ba1ac4429b697ae78199f33c8', 23, 2, '2021-06-18 09:05:02', 20);
INSERT INTO `appoints` VALUES (84, '089fd00ba1ac4429b697ae78199f33c8', 16, 2, '2021-06-15 09:05:45', 10);
INSERT INTO `appoints` VALUES (85, '089fd00ba1ac4429b697ae78199f33c8', 31, 2, '2021-06-16 09:06:15', 80);
INSERT INTO `appoints` VALUES (86, '089fd00ba1ac4429b697ae78199f33c8', 13, 2, '2021-06-17 09:08:47', 40);
INSERT INTO `appoints` VALUES (87, '089fd00ba1ac4429b697ae78199f33c8', 24, 2, '2021-06-16 09:11:42', 80);
INSERT INTO `appoints` VALUES (88, '089fd00ba1ac4429b697ae78199f33c8', 21, 2, '2021-06-20 09:24:42', 50);
INSERT INTO `appoints` VALUES (89, '089fd00ba1ac4429b697ae78199f33c8', 21, 2, '2021-06-23 09:35:25', 840);

-- ----------------------------
-- Table structure for charges
-- ----------------------------
DROP TABLE IF EXISTS `charges`;
CREATE TABLE `charges`  (
  `cusid` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `length` int(11) NULL DEFAULT NULL,
  `time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`cusid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charges
-- ----------------------------
INSERT INTO `charges` VALUES (28, '1', 11, NULL);
INSERT INTO `charges` VALUES (29, '3', 10, '2021-06-21 17:29:24.000000');
INSERT INTO `charges` VALUES (30, '3', 20, '2021-06-21 17:29:47.000000');
INSERT INTO `charges` VALUES (31, '3', 40, '2021-06-21 17:34:11.000000');
INSERT INTO `charges` VALUES (32, '3', 80, '2021-06-21 17:34:39.000000');
INSERT INTO `charges` VALUES (33, '3', 10, '2021-06-22 17:15:38.000000');
INSERT INTO `charges` VALUES (34, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:18:01.000000');
INSERT INTO `charges` VALUES (35, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:20:48.000000');
INSERT INTO `charges` VALUES (36, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:31:16.000000');
INSERT INTO `charges` VALUES (37, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:33:04.000000');
INSERT INTO `charges` VALUES (38, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:37:09.000000');
INSERT INTO `charges` VALUES (39, '089fd00ba1ac4429b697ae78199f33c8', 20, '2021-06-22 17:42:06.000000');
INSERT INTO `charges` VALUES (40, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:53:12.000000');
INSERT INTO `charges` VALUES (41, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:53:39.000000');
INSERT INTO `charges` VALUES (42, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:58:26.000000');
INSERT INTO `charges` VALUES (43, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:58:29.000000');
INSERT INTO `charges` VALUES (44, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:59:15.000000');
INSERT INTO `charges` VALUES (45, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 17:59:21.000000');
INSERT INTO `charges` VALUES (46, '089fd00ba1ac4429b697ae78199f33c8', 20, '2021-06-22 18:40:57.000000');
INSERT INTO `charges` VALUES (47, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 18:44:20.000000');
INSERT INTO `charges` VALUES (48, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 18:44:44.000000');
INSERT INTO `charges` VALUES (49, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-22 18:45:06.000000');
INSERT INTO `charges` VALUES (50, '1', 10, '2015-07-08 11:32:00.000000');
INSERT INTO `charges` VALUES (51, '089fd00ba1ac4429b697ae78199f33c8', 10, '2021-06-23 00:37:55.000000');
INSERT INTO `charges` VALUES (52, '089fd00ba1ac4429b697ae78199f33c8', 20, '2021-06-23 00:38:15.000000');
INSERT INTO `charges` VALUES (53, '089fd00ba1ac4429b697ae78199f33c8', 20, '2021-06-23 06:37:34.000000');
INSERT INTO `charges` VALUES (54, '089fd00ba1ac4429b697ae78199f33c8', 50, '2021-06-23 09:08:36.000000');
INSERT INTO `charges` VALUES (55, '089fd00ba1ac4429b697ae78199f33c8', 20, '2021-06-23 09:25:35.000000');

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `customerid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customer_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mem_length` int(11) NOT NULL,
  `res_status` int(11) NOT NULL,
  PRIMARY KEY (`customerid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('089fd00ba1ac4429b697ae78199f33c8', '自学上岸', 19, 0);
INSERT INTO `customers` VALUES ('1', NULL, 0, 0);
INSERT INTO `customers` VALUES ('100', NULL, 123, 0);
INSERT INTO `customers` VALUES ('111', NULL, 45, 0);
INSERT INTO `customers` VALUES ('112', NULL, 78, 1);
INSERT INTO `customers` VALUES ('2', NULL, 0, 0);
INSERT INTO `customers` VALUES ('3', NULL, 10, 0);
INSERT INTO `customers` VALUES ('b155877b52fb13d156512972a56bc05d', '小宇', 0, 0);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (90);

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms`  (
  `ss_roomid` int(11) NOT NULL,
  `address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cover_map` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loc_num` int(11) NOT NULL,
  `ss_room_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ss_room_type` int(11) NULL DEFAULT NULL,
  `use_num` int(11) NOT NULL,
  PRIMARY KEY (`ss_roomid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES (2, '四川省成都市青羊区人民西路4号天府广场旁', 'https://study-room.obs.cn-north-4.myhuaweicloud.com/library_2.jpg', NULL, 45, '省图书馆自习室', NULL, 9);
INSERT INTO `rooms` VALUES (5, '四川省成都市武侯区望江路1号中海广场1106室', 'https://study-room.obs.cn-north-4.myhuaweicloud.com/library_1.jpg', NULL, 50, '西柚自习室', NULL, 35);
INSERT INTO `rooms` VALUES (12, '四川省成都市龙泉驿区成龙大道二段1819号四川师范大学成龙校区图书馆三楼', 'https://study-room.obs.cn-north-4.myhuaweicloud.com/library_4.jpg', NULL, 6, '星梦书屋（师大店）', NULL, 6);
INSERT INTO `rooms` VALUES (20, '四川省成都市青羊区光华中心C座407号', 'https://study-room.obs.cn-north-4.myhuaweicloud.com/library_3.jpg', NULL, 130, 'K书吧自习空间', NULL, 55);

-- ----------------------------
-- Table structure for seats
-- ----------------------------
DROP TABLE IF EXISTS `seats`;
CREATE TABLE `seats`  (
  `ss_roomid` int(11) NOT NULL,
  `seatid` int(11) NOT NULL,
  `seat_status` int(11) NULL DEFAULT NULL,
  `seat_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ss_roomid`, `seatid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seats
-- ----------------------------
INSERT INTO `seats` VALUES (1, 1, 1, 1);
INSERT INTO `seats` VALUES (1, 2, 0, 1);
INSERT INTO `seats` VALUES (1, 3, 1, 1);
INSERT INTO `seats` VALUES (2, 1, 0, 1);
INSERT INTO `seats` VALUES (2, 2, 0, 1);
INSERT INTO `seats` VALUES (2, 3, 0, 0);
INSERT INTO `seats` VALUES (2, 5, 0, 0);
INSERT INTO `seats` VALUES (2, 6, 0, 0);
INSERT INTO `seats` VALUES (2, 7, 0, 0);
INSERT INTO `seats` VALUES (2, 8, 0, 0);
INSERT INTO `seats` VALUES (2, 9, 0, 0);
INSERT INTO `seats` VALUES (2, 10, 0, 0);
INSERT INTO `seats` VALUES (2, 11, 0, 0);
INSERT INTO `seats` VALUES (2, 12, 0, 0);
INSERT INTO `seats` VALUES (2, 13, 0, 0);
INSERT INTO `seats` VALUES (2, 14, 0, 0);
INSERT INTO `seats` VALUES (2, 15, 0, 0);
INSERT INTO `seats` VALUES (2, 16, 0, 0);
INSERT INTO `seats` VALUES (2, 17, 0, 0);
INSERT INTO `seats` VALUES (2, 18, 0, 0);
INSERT INTO `seats` VALUES (2, 19, 0, 0);
INSERT INTO `seats` VALUES (2, 20, 0, 0);
INSERT INTO `seats` VALUES (2, 21, 0, 0);
INSERT INTO `seats` VALUES (2, 22, 0, 0);
INSERT INTO `seats` VALUES (2, 23, 0, 0);
INSERT INTO `seats` VALUES (2, 24, 0, 0);
INSERT INTO `seats` VALUES (2, 25, 0, 0);
INSERT INTO `seats` VALUES (2, 26, 0, 0);
INSERT INTO `seats` VALUES (2, 27, 0, 0);
INSERT INTO `seats` VALUES (2, 28, 0, 0);
INSERT INTO `seats` VALUES (2, 29, 0, 0);
INSERT INTO `seats` VALUES (2, 30, 0, 0);
INSERT INTO `seats` VALUES (2, 31, 0, 0);
INSERT INTO `seats` VALUES (2, 32, 0, 0);
INSERT INTO `seats` VALUES (2, 33, 0, 0);
INSERT INTO `seats` VALUES (2, 34, 0, 0);
INSERT INTO `seats` VALUES (2, 35, 0, 0);
INSERT INTO `seats` VALUES (2, 36, 0, 0);
INSERT INTO `seats` VALUES (2, 37, 0, 0);
INSERT INTO `seats` VALUES (2, 38, 0, 0);
INSERT INTO `seats` VALUES (2, 39, 0, 0);
INSERT INTO `seats` VALUES (2, 40, 0, 0);
INSERT INTO `seats` VALUES (2, 41, 0, 0);
INSERT INTO `seats` VALUES (2, 42, 0, 0);
INSERT INTO `seats` VALUES (2, 43, 0, 0);
INSERT INTO `seats` VALUES (2, 44, 0, 0);
INSERT INTO `seats` VALUES (2, 45, 0, 0);
INSERT INTO `seats` VALUES (2, 46, 0, 0);
INSERT INTO `seats` VALUES (2, 47, 0, 0);
INSERT INTO `seats` VALUES (2, 48, 0, 0);
INSERT INTO `seats` VALUES (2, 49, 0, 0);
INSERT INTO `seats` VALUES (2, 50, 0, 0);

-- ----------------------------
-- Procedure structure for appointsAdd
-- ----------------------------
DROP PROCEDURE IF EXISTS `appointsAdd`;
delimiter ;;
CREATE PROCEDURE `appointsAdd`()
BEGIN
	DECLARE i INT DEFAULT 0;
	WHILE
			i < ( SELECT COUNT( DISTINCT ( customerid ) ) FROM `appoints`) DO
			INSERT INTO appoints ( customerid, seatid, ss_roomid, start_time, study_len )
		VALUES
			( ( SELECT DISTINCT ( customerid ) FROM (SELECT customerid FROM appoints) AS id LIMIT i, 1 ), 0, 0, CURDATE( ), 0 ) ;
		SET i = i + 1;
	END WHILE;-- 循环结束
END
;;
delimiter ;

-- ----------------------------
-- Event structure for AddApoint
-- ----------------------------
DROP EVENT IF EXISTS `AddApoint`;
delimiter ;;
CREATE EVENT `AddApoint`
ON SCHEDULE
EVERY '20' SECOND STARTS '2021-06-22 18:38:39'
DO CALL appointsAdd()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
