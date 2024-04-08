# 创建数据库
create database test01;

# 删除数据库
drop database test01;

# 创建指定字符集和校对规则的数据库
# 校对规则 utf8_bin 区分大小写 uft8_general_ci 不区分大小写
create database test01 character set utf8 collate utf8_bin;

# 查看当前数据库服务器中的所有数据库
SHOW DATABASES;

# 查看数据库 test01 的定义信息（在创建数据库、表的时候，为了规避关键字，可以使用反引号解决）
SHOW CREATE DATABASE `test01`;

# 删除数据库
DROP DATABASE `test01`;

# 数据据的备份与恢复
# 这个备份的文件就是对应的sql语句（创建表、插入数据等）
# 备份(要在Dos下执行mysqldump指令，其实是在mysql安装目录\bin下) 备份时最好使用管理员运行cmd，不要用powershell，防止出现中文乱码的问题
# mysqldump -u root -proot -B db01 test01 > D:\WorkSpace_BackEnd\MySQL\bak\20240406.sql

# 恢复数据库（注意 进入MySQL命令行再执行） 或直接粘贴到SQL编辑器中执行
# source D:\WorkSpace_BackEnd\MySQL\bak\20240406.sql

# 创建表
CREATE TABLE `user_cmd` (
    `id` int(11),
    `name` varchar(255),
    `password` varchar(255),
    `birthday` date
)
    CHARACTER SET utf8 COLLATE utf8_bin;

# 表数范围 没有指定 unsinged 则tinyint就是有符号的
CREATE TABLE test01.t2 (
    `id` TINYINT
);

# 向表中新添加字段（添加到表的末尾）unsigned 无符号 tinyint
# ALTER TABLE test01.t2 ADD age TINYINT unsigned FIRST 添加到表的开头
# ALTER TABLE test01.t2 ADD age TINYINT unsigned AFTER <已存在的字段名>
ALTER TABLE test01.t2 ADD age TINYINT unsigned;

INSERT INTO test01.t2 values(0, 255);

SELECT * FROM test01.t2;

# 演示bit类型的使用
# 1、bit(m) m 表示几位bit 范围为 1-64
# 2、添加的数据范围按照指定的位数来确定 例如 m 为8，则表示一个字节范围为0~255
# 3、显示时按照二进制显示
# 4、查询时，任然可以按照数值来查询
ALTER TABLE test01.t2 ADD `bit_test` bit(8);

INSERT INTO test01.t2 values(1, 0, 11);

SELECT * FROM test01.t2 WHERE `bit_test` = 11;

# 演示float double decimal
CREATE TABLE test01.t3 (
                           num1 float,
                           num2 double,
                           num3 decimal(30, 20)
);

INSERT INTO test01.t3 values(88.12345678912345, 88.12345678912345, 88.12345678912345);

SELECT * FROM test01.t3;

# 演示char varchar
# char(4) 和 varchar(4) 这个4表示4个字符 不区分是汉字还是字母
# INSERT INTO test01.t4 values('你好世界a', '11') 也添加不进去，去掉 'a' 就可以了
CREATE TABLE test01.t4 (
                           `name_char` char(4),
                           `name_varchar` varchar(4)
);

ALTER TABLE test01.t4 ADD `content` text; -- text mysql不允许有默认值

INSERT INTO test01.t4 values('abcd', '11', '好10~'); -- utf8编码下 其中content占用的大小为 1 * 3 + 3 = 6个字节，如果是varchar则是 4*3 个字节

SELECT * FROM test01.t4;

# 演示时间相关的类型
CREATE TABLE test01.t5 (
   `birthday` date,
   `job_time` datetime,
   `login_time` timestamp
       NOT NULL DEFAULT current_timestamp
       ON UPDATE current_timestamp -- 登陆时间 如果希望login_time列自动更新需要加上 ON UPDATE current_timestamp 以当前时间更新
);

# INSERT时如果不指定 login_time 则按照 DEFAULT current_timestamp 添加默认的当前时间(current_timestamp)进去
# 当表中某一条数据更新时，login_time 也会按照 ON UPDATE current_timestamp 自动更新
INSERT INTO test01.t5(birthday, job_time) values('2022-11-11', '2022-11-11 10:10:10');

SELECT * FROM test01.t5;
