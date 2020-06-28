/*
 Navicat MySQL Data Transfer

 Source Server         : sb念总server
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 47.107.147.219:3306
 Source Schema         : coopte_online

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 27/06/2020 21:25:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_text
-- ----------------------------
DROP TABLE IF EXISTS `t_text`;
CREATE TABLE `t_text`  (
  `text_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `text_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`text_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
