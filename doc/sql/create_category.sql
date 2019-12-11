drop table if exists pms_category;

/*==============================================================*/
/* Table: pms_category                                          */
/*==============================================================*/
create table pms_category
(
   id                   bigint(20) unsigned NOT NULL auto_increment not null comment '分类ID',
   pid                  bigint(20) unsigned NOT NULL DEFAULT '0' comment '父ID',
   category_name        varchar(50) NOT NULL DEFAULT '' comment '分类名称',
   child_id             varchar(255) NOT NULL DEFAULT '' comment '子id 以逗号分隔',
   description          varchar(255)  NOT NULL DEFAULT '' comment '分类描述',
   create_time          int(11) unsigned NOT NULL DEFAULT '0' comment '创建时间',
   update_time          int(11) unsigned NOT NULL DEFAULT '0' comment '更新时间',
   primary key (id)
)
ENGINE = InnoDB
default charset = UTF8;

alter table pms_category comment '分类信息表';