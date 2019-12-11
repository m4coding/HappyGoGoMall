

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jd_product_items`
-- ----------------------------
DROP TABLE IF EXISTS `jd_product_items`;
CREATE TABLE `jd_product_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword` varchar(255) NOT NULL DEFAULT '' COMMENT '搜索关键字',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名称',
  `store` varchar(255) NOT NULL DEFAULT '' COMMENT '商家名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `link` varchar(255) NOT NULL DEFAULT '' COMMENT '详情页链接',
  `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '评论数',
  `img` varchar(255) NOT NULL DEFAULT '' COMMENT '封面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;