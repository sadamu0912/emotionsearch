CREATE TABLE `user` (
  `increment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(33) NOT NULL,
  `user_name` varchar(33) NOT NULL,
  `password` varchar(33) DEFAULT '1' COMMENT '是否启用',
  `del` int(5) DEFAULT NULL COMMENT '头像',
  `create_userid` varchar(33) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_userid` varchar(33) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_number` varchar(33) DEFAULT NULL,
  `enable` int(5) DEFAULT NULL,
  PRIMARY KEY (`increment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;



CREATE TABLE `conf_menu` (
  `increment_id` int(11) NOT NULL,
  `menu_id` varchar(32) NOT NULL,
  `menu_parentid` varchar(32) DEFAULT NULL,
  `menu_code` varchar(32) DEFAULT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_icon` varchar(50) DEFAULT NULL,
  `menu_href` varchar(100) DEFAULT NULL,
  `target_type` varchar(32) NOT NULL,
  `menu_depth` int(5) DEFAULT NULL,
  `menu_order` int(3) NOT NULL,
  `menu_desc` varchar(200) DEFAULT NULL,
  `menu_parentcode` varchar(32) DEFAULT NULL,
  `create_userid` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_userid` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `module_id` varchar(32) DEFAULT NULL,
  `del` int(5) NOT NULL,
  PRIMARY KEY (`increment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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

CREATE TABLE `photo` (
  `increment_id` int(11) NOT NULL,
  `photo_id` varchar(32) NOT NULL,
  `photo_name` varchar(400) NOT NULL,
  `photo_path` varchar(300) NOT NULL COMMENT '图片存储路径',
  `photo_type_code` varchar(50) DEFAULT NULL,
  `photo_type_name` varchar(300) DEFAULT NULL,
  `relation_id` varchar(32) DEFAULT '' COMMENT '关联项目id',
  `relation_type` varchar(50) DEFAULT NULL COMMENT '关联项目类型',
  `photo_size` double(20,0) DEFAULT NULL COMMENT '单位是kb',
  `photo_desc` varchar(500) DEFAULT NULL,
  `del` int(5) NOT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


