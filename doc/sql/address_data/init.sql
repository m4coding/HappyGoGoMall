SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS province;
CREATE TABLE province (
  _id            int comment '主键',
  name           varchar(64) comment '省名称',
  province_id      varchar(12) comment '省id',
  PRIMARY KEY (_id)
);


DROP TABLE IF EXISTS city;
CREATE TABLE city (
  _id           int comment '主键',
  name          varchar(64) comment '城市名称',
  city_id       varchar(12) comment '城市id',
  province_id   varchar(12) comment '对应的省id',
  PRIMARY KEY (_id)
);


DROP TABLE IF EXISTS county;
CREATE TABLE county (
  _id  int comment '主键',
  name varchar(64) comment '区名称',
  county_id varchar(12) comment '区id',
  city_id varchar(12) comment '对应的城市id',
  PRIMARY KEY (_id)
);


DROP TABLE IF EXISTS town;
CREATE TABLE town (
  _id  int comment '主键',
  name varchar(64) comment '城镇名称',
  town_id varchar(12) comment '城镇id',
  county_id varchar(12) comment '对于的区id',
  PRIMARY KEY (_id)
);


DROP TABLE IF EXISTS village;
CREATE TABLE village (
  _id  int comment '主键',
  name varchar(64) comment '村庄名称',
  village_id varchar(12) comment '村庄id',
  town_id varchar(12) comment '对应的城镇id',
  PRIMARY KEY (_id)
);