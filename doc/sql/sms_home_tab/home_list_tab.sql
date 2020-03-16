drop table if exists sms_home_tab;

/*==============================================================*/
/* Table: sms_home_tab                                          */
/*==============================================================*/
create table sms_home_tab
(
   id                   bigint(20) not null,
   title                varchar(100) comment '标题',
   subtitle             varchar(100) comment '子标题',
   type                 varchar(100) comment 'tab类型',
   primary key (id)
)
ENGINE = InnoDB
default charset = UTF8;

alter table sms_home_tab comment '首页列表tab';
