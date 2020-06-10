/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2020-06-10 10:44:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mb_goods
-- ----------------------------
DROP TABLE IF EXISTS `mb_goods`;
CREATE TABLE `mb_goods` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mb_goods
-- ----------------------------
INSERT INTO `mb_goods` VALUES ('12', '商品名称', '120');
