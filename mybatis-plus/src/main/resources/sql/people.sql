/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : 65001

 Date: 08/08/2019 09:57:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `manager_fk`(`manager_id`) USING BTREE,
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `people` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1087982257332887551, 'dyz-ann', 18, 'dyz@163.com', NULL, '2019-07-09 10:00:34');
INSERT INTO `people` VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20');
INSERT INTO `people` VALUES (1088248166370832385, '王天风', 25, 'wtf@baomidou.com', 1087982257332887553, '2019-02-05 11:12:22');
INSERT INTO `people` VALUES (1088250446457389058, '李艺伟', 28, 'lyw@baomidou.com', 1088248166370832385, '2019-02-14 08:31:16');
INSERT INTO `people` VALUES (1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15');
INSERT INTO `people` VALUES (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');

SET FOREIGN_KEY_CHECKS = 1;
