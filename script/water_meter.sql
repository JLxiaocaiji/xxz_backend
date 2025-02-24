/*
 Navicat Premium Data Transfer

 Source Server         : 116.205.245.115
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : water_meter

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 14/08/2023 10:27:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `device_number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '设备唯一编码',
                           `device_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '设备名称',
                           `create_time` datetime NOT NULL,
                           `update_time` datetime NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `index_deviceNumber`(`device_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 503 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for instruction
-- ----------------------------
DROP TABLE IF EXISTS `instruction`;
CREATE TABLE `instruction`  (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `device_id` int NOT NULL COMMENT '设备唯一id',
                                `device_number` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '设备唯一编码',
                                `type` tinyint NOT NULL COMMENT '指令类型0:测试1:获取设备id2:上报识别结果3:上报结果和图片',
                                `status` tinyint NOT NULL COMMENT '指令执行状态1:未处理2:已发送3:已响应',
                                `receive` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '指令回复',
                                `receive_pic` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '指令回复提取核心属性',
                                `create_time` datetime NOT NULL,
                                `update_time` datetime NOT NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `index_deviceNumber`(`device_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1022 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
