# 说明

pdm格式的文件是PowerDesigner的项目文件

Workspace.sws是PowderDesigner的workspace文件

[PowerDesigner生成数据库时的列中文注释乱码问题的设置方法](https://blog.csdn.net/weixin_30825199/article/details/99012622)

[PowerDesigner怎么跟表的字段加注释](https://jingyan.baidu.com/article/59a015e358477ff794886523.html)

[PowerDesigner 在建表语句后面添加engine 和字符编码](https://blog.csdn.net/qq_40414209/article/details/100915490)

[MYSQL: Cannot delete or update a parent row: a foreign key constraint fails](https://blog.csdn.net/yabingshi_tech/article/details/52290124)

[（一）购物商城数据库设计-前期准备](https://blog.csdn.net/thc1987/article/details/80426006)

[（二）购物商城数据库设计-商品表设计](https://blog.csdn.net/thc1987/article/details/80426063)

[（三）购物商城数据库设计-商品表DDL（Mysql）](https://blog.csdn.net/thc1987/article/details/80502513)

[《电商设计手册》](http://skrshop.tech/#/)

[日期用这种方式来存储可能更加优雅](http://objcoding.com/2019/06/21/timestamp/)

### 数据表统一设计规范

    1、表名，全部小写，采用简单的单词做前缀，用来区分业务
    2、表中必须有id,create_time,update_time这三个字段
    3、字段，小写字母加下划线组成，不用简写（用image，不用img）
    4、字段名单词尽量简单，不用生僻单词，不用拼音
    5、字段类型尽量选择小的，能用tinyint时不用int
    6、字段要有注释
    7、表编码采用UTF-8

### navicat创建数据库过程

1、先创建一个数据库

2、然后再运行对应的创建表的sql语句


### 表结构

用户模块

    ums_user  用户信息表
    ums_user_auth 用户认证信息表

商品模块

    pms_product  商品信息表
    
    
### 一些奇怪的问题

1、通过PowerDesigner转出的sql文件，在navicat运行后没啥问题，如果直接copy sql代码出来到另一个文件再执行，就会出现注释中文乱码现象。。。