/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-01-07 20:54:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `increment_id` int(11) NOT NULL,
  `project_id` varchar(32) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `project_progress` float(6,0) DEFAULT NULL COMMENT '项目进度',
  `project_state` varchar(30) DEFAULT NULL COMMENT '项目状态，比如说在开发，已经付款，。。',
  `project_image` varchar(50) DEFAULT NULL COMMENT '小程序二维码路径',
  `project_view_count` int(20) DEFAULT NULL COMMENT '小程序的被用户查看次数',
  `project_love_count` int(20) DEFAULT NULL,
  `project_comment_count` int(20) DEFAULT NULL,
  `project_customer_city` varchar(300) DEFAULT NULL COMMENT '小程序客户的坐落城市',
  `project_customer_city_code` varchar(50) DEFAULT NULL,
  `project_customer_name` varchar(30) DEFAULT NULL COMMENT '小程序客户（商家）的对接人名称',
  `project_customer_phone` varchar(20) DEFAULT NULL COMMENT '联系方式 电话号码',
  `project_cusomer_email` varchar(30) DEFAULT NULL COMMENT '联系方式 邮箱',
  `project_type_name` varchar(30) DEFAULT NULL COMMENT '项目类型  比如说电商  娱乐   工具等等',
  `project_type_code` varchar(15) DEFAULT NULL,
  `create_userid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_userid` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del` int(5) DEFAULT NULL,
  `project_state_code` varchar(10) DEFAULT NULL COMMENT '状态code',
  PRIMARY KEY (`increment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '6e56621cfc7e4e2986fa132205f80c43', '朝夕选课', '50', '在开发中', '/images/project/朝夕选课.jpg', '100', '20', '300', '浙江省杭州市下城区长城里', '341223', '徐某某', '18767161111', '1147611111@qq.com', '阅读', 'yuedu', 'admin', '2018-01-07 16:57:27', 'admin', '2018-01-07 16:57:36', '0', 'kf');
INSERT INTO `project` VALUES ('2', '540f4be01a65473ab9c369805fdae2fd', '房贷计算器专业版', '60', '在开发中', '/images/project/房贷计算器专业版.jpg', '200', '50', '600', '浙江省湖州市安吉县', '330523', '张某某', '18767161112', '1147611112@qq.com', '工具', 'gongju', 'admin', '2018-01-07 17:00:29', 'admin', '2018-01-07 17:00:35', '0', 'kf');
