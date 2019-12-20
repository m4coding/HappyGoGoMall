drop table if exists oms_company_address;

/*==============================================================*/
/* Table: oms_company_address                                   */
/*==============================================================*/
create table oms_company_address
(
   id                   bigint(20) not null auto_increment,
   address_name         varchar(200) default NULL comment '地址名称',
   send_status          int(1) default NULL comment '默认发货地址：0->否；1->是',
   receive_status       int(1) default NULL comment '是否默认收货地址：0->否；1->是',
   name                 varchar(64) default NULL comment '收发货人姓名',
   phone                varchar(64) default NULL comment '收货人电话',
   province             varchar(64) default NULL comment '省/直辖市',
   city                 varchar(64) default NULL comment '市',
   region               varchar(64) default NULL comment '区',
   detail_address       varchar(200) default NULL comment '详细地址',
   primary key (id)
)
ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公司收发货地址表';

alter table oms_company_address comment '公司收发货地址表';