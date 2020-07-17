drop table if exists oms_cart_item;

/*==============================================================*/
/* Table: oms_cart_item                                         */
/*==============================================================*/
create table oms_cart_item
(
   id                   int not null auto_increment comment 'id主键',
   product_sku_id       bigint(20) Not Null comment 'sku id',
   user_id              bigint(20) Not Null comment '对应的用户id',
   quantity             bigint(11) Not Null comment '加到购物车的数量',
   price                decimal(10,2) DEFAULT 0 comment '加到购物车时的价格',
   create_date          int(11) unsigned NOT NULL DEFAULT 0 comment '创建时间',
   modify_date          int(11) unsigned NOT NULL DEFAULT 0 comment '修改时间',
   delete_status        int(1) DEFAULT 0 comment '删除状态  0表示非删除状态、1表示删除状态',
   brand_id             varchar(200) DEFAULT NULL comment '品牌id',
   category_id          varchar(200) DEFAULT NULL comment '分类id',
   item_status          int(4) unsigned NOT NULL DEFAULT 0 comment '商品在购物车中的状态: 0-新增（未选中）, 1-选中',
   primary key (id)
)
ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='购物车列表数据表';

alter table oms_cart_item comment '购物车列表数据表';