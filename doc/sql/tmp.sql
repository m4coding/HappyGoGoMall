drop table if exists sms_home_advertise;

/*==============================================================*/
/* Table: sms_home_advertise                                    */
/*==============================================================*/
create table sms_home_advertise
(
   id                   bigint(20) not null auto_increment,
   name                 varchar(100) default NULL,
   type                 int(1) default NULL comment '轮播位置：0->PC首页轮播；1->app首页轮播',
   pic                  varchar(500) default NULL,
   start_time           int(11) not null default 0 ,
   end_time             int(11) not null default 0 ,
   status               int(1) default NULL comment '上下线状态：0->下线；1->上线',
   click_count          int(11) default NULL comment '点击数',
   order_count          int(11) default NULL comment '下单数',
   url                  varchar(500) default NULL comment '链接地址',
   note                 varchar(500) default NULL comment '备注',
   sort                 int(11) default 0 comment '排序',
   primary key (id)
)
ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='首页轮播广告表';