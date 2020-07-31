drop table if exists ums_user_receiver_address;

/*==============================================================*/
/* Table: ums_user_receiver_address                             */
/*==============================================================*/
create table ums_user_receiver_address
(
   id                   bigint(20) NOT NULL AUTO_INCREMENT not null comment '主键id',
   user_id              bigint(20) DEFAULT NULL comment '用户id',
   phone_number         varchar(64) DEFAULT NULL comment '电话号码',
   default_status       int(1) DEFAULT NULL comment '是否为默认 1为默认，0不是',
   post_code            varchar(100) DEFAULT NULL comment '邮政编码',
   province             varchar(100) DEFAULT NULL comment '省份/直辖市',
   city                 varchar(100) DEFAULT NULL comment '城市',
   region               varchar(100) DEFAULT NULL comment '区',
   detail_address       varchar(128) DEFAULT NULL comment '详细地址(街道)',
   primary key (id)
)
ENGINE = InnoDB
default charset = UTF8;

alter table ums_user_receiver_address comment '用户的收货地址表';