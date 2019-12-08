

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` varchar(100) NOT NULL COMMENT '商品名称',
  `description` text COMMENT '商品描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `purchase_price` decimal(10,0) NOT NULL COMMENT '商品采购价',
  `market_price` decimal(10,0) NOT NULL COMMENT '市场价格',
  `sale_price` decimal(10,0) DEFAULT NULL COMMENT '销售价格',
  `category_id` int(11) NOT NULL COMMENT '类别ID',
  `icon` text DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员名称',
  `admin_status` int(11) DEFAULT NULL COMMENT '管理员状态 1表示启用状态，0表示禁用状态',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ums_admin_auth
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_auth`;
CREATE TABLE `ums_admin_auth` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证ID',
  `admin_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `identity_type` varchar(200) NOT NULL COMMENT '登录类型  如：admin表示系统用户，phone表示手机登录，weixin表示微信登录，qq表示qq登录',
  `identity` varchar(500) NOT NULL COMMENT '识别标识  如：登录账号、邮箱地址、手机号、微信号、qq号等',
  `certificate` varchar(500) NOT NULL COMMENT '授权凭证  如：登录账号对应的是密码、第三方登录对应的是token',
  `if_verify` tinyint(1) DEFAULT NULL COMMENT '是否验证 true表示已被验证，false表示未验证',
  PRIMARY KEY (`auth_id`),
  KEY `FK_Reference_2` (`admin_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`admin_id`) REFERENCES `ums_admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `ip` text COMMENT 'IP地址',
  `user_agent` text COMMENT '客户端登录类型',
  `address` text COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员登录log表';

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `status` int(11) DEFAULT NULL COMMENT '用户状态 1表示启用状态，0表示禁用状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Table structure for ums_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_auth`;
CREATE TABLE `ums_user_auth` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `identity_type` varchar(200) NOT NULL COMMENT '登录类型  如：admin表示系统用户，phone表示手机登录，weixin表示微信登录，qq表示qq登录',
  `identity` varchar(500) NOT NULL COMMENT '识别标识  如：登录账号、邮箱地址、手机号、微信号、qq号等',
  `certificate` varchar(500) NOT NULL COMMENT '授权凭证  如：登录账号对应的是密码、第三方登录对应的是token',
  `if_verify` tinyint(1) DEFAULT NULL COMMENT '是否验证   true表示已被验证，false表示未验证',
  PRIMARY KEY (`auth_id`),
  KEY `FK_Reference_1` (`user_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `ums_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证表';

SET FOREIGN_KEY_CHECKS = 1;
