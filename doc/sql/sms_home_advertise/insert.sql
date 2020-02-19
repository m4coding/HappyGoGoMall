-- SET NAMES utf8mb4;
-- SET FOREIGN_KEY_CHECKS = 0;
--
-- -- ----------------------------
-- -- Table structure for sms_home_advertise
-- -- ----------------------------
-- DROP TABLE IF EXISTS `sms_home_advertise`;
-- CREATE TABLE `sms_home_advertise` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT,
--   `name` varchar(100) DEFAULT NULL,
--   `type` int(1) DEFAULT NULL COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播',
--   `pic` varchar(500) DEFAULT NULL,
--   `start_time` int(11) NOT NULL DEFAULT '0',
--   `end_time` int(11) NOT NULL DEFAULT '0',
--   `status` int(1) DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
--   `click_count` int(11) DEFAULT NULL COMMENT '点击数',
--   `order_count` int(11) DEFAULT NULL COMMENT '下单数',
--   `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
--   `note` varchar(500) DEFAULT NULL COMMENT '备注',
--   `sort` int(11) DEFAULT '0' COMMENT '排序',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='首页轮播广告表';
--
-- SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `sms_home_advertise` VALUES ('1', '彩电品牌日', '1', 'http://120.79.180.114/images/product/ad/20200219/tv.png', '1577858497', '1605420097', '1', '0', '0', null, '', '0');
INSERT INTO `sms_home_advertise` VALUES ('2', '电脑', '1', 'http://120.79.180.114/images/product/ad/20200219/computer.png', '1577858497', '1605420097', '1', '0', '0', null, '', '0');
INSERT INTO `sms_home_advertise` VALUES ('3', '鞋子', '1', 'http://120.79.180.114/images/product/ad/20200219/shoe.png', '1577858497', '1605420097', '1', '0', '0', null, '', '0');
INSERT INTO `sms_home_advertise` VALUES ('4', '科沃斯广告', '1', 'http://120.79.180.114/images/product/ad/20200219/kewos.png', '1577858497', '1605420097', '1', '0', '0', 'www.baidu.com', '', '100');
INSERT INTO `sms_home_advertise` VALUES ('5', '家装广告', '1', 'http://120.79.180.114/images/product/ad/20200219/home.png', '1577858497', '1605420097', '1', '0', '0', 'xxx', null, '99');
INSERT INTO `sms_home_advertise` VALUES ('6', '智能家电', '1', 'http://120.79.180.114/images/product/ad/20200219/home-smart.png', '1577858497', '1605420097', '1', '0', '0', 'xxx', null, '98');
INSERT INTO `sms_home_advertise` VALUES ('7', '超酷智能家电', '1', 'http://120.79.180.114/images/product/ad/20200219/home-smart2.png', '1577858497', '1605420097', '1', '0', '0', 'xxx', null, '98');
