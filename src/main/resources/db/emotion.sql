
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `emotion`;
CREATE DATABASE `emotion`;
USE `emotion`;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `role_name` varchar(32) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `role_code` varchar(20) NOT NULL COMMENT '角色代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
BEGIN;
INSERT INTO `emotion`.`role` (`id`, `role_id`, `role_name`, `role_desc`, `role_code`) VALUES ('1', 'de42c0682bc14bc2a60b180afc1f73f2', '管理员', '', 'admin');
INSERT INTO `emotion`.`role` (`id`, `role_id`, `role_name`, `role_desc`, `role_code`) VALUES ('2', '08a9b8933af849229152c66deac4441b', '会员', NULL, 'member');
INSERT INTO `emotion`.`role` (`id`, `role_id`, `role_name`, `role_desc`, `role_code`) VALUES ('3', '6e56621cfc7e4e2986fa132205f80c43', '普通用户', NULL, 'normal');
COMMIT;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `user_name` varchar(32) NOT NULL COMMENT '用户真实姓名',
  `user_account` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(32) DEFAULT NULL,
  `enable` int(2) DEFAULT '1' COMMENT '默认1 ，0代表删除，1代表可用',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone_number` int(20) DEFAULT NULL COMMENT '电话号码',
  `third_party_account` varchar(50) DEFAULT NULL COMMENT '第三方账号',
  `createTime` datetime NOT NULL,
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最近登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
BEGIN;
INSERT INTO `emotion`.`user` (`id`, `user_id`, `user_name`, `user_account`, `password`, `enable`, `avatar`, `email`, `phone_number`, `third_party_account`, `create_time`, `last_login_time`) VALUES ('1', 'ac9567d5fd0b4c539ccc90ac57f015c1', 'admin', 'admin', '55b3d0936a3fb63168d57a6bda0ddbbf', '1', NULL, NULL, NULL, NULL, '2019-08-02 00:59:31', '2019-08-02 00:59:35');
COMMIT;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `emotion`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('1', 'ac9567d5fd0b4c539ccc90ac57f015c1', 'de42c0682bc14bc2a60b180afc1f73f2');



