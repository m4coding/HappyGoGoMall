# 预置数据

-- 服装
INSERT INTO pms_category(category_name,create_time, update_time)
    SELECT "服装", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "服装");

SET @pid = (SELECT id FROM pms_category WHERE category_name = "服装");

INSERT INTO pms_category(pid,category_name,create_time, update_time)
    SELECT @pid,"女装", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "女装");

INSERT INTO pms_category(pid,category_name,create_time, update_time)
    SELECT @pid,"男装", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "男装");

UPDATE pms_category INNER JOIN (SELECT id FROM pms_category WHERE category_name = "女装" OR category_name = "男装") xx SET child_id = xx WHERE category_name = "服装";


-- 医药保健
INSERT INTO pms_category(category_name,create_time, update_time)
SELECT "医药保健", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "医药保健");

SET @pid = (SELECT id FROM pms_category WHERE category_name = "医药保健");

INSERT INTO pms_category(pid,category_name,create_time, update_time)
SELECT @pid,"中西药品", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "中西药品");

INSERT INTO pms_category(pid,category_name,create_time, update_time)
SELECT @pid,"营养成分", unix_timestamp(now()), unix_timestamp(now()) FROM DUAL WHERE NOT EXISTS (SELECT id FROM pms_category WHERE category_name = "营养成分");