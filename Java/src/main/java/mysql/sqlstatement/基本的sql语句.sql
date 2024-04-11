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

# 创建表练习
CREATE TABLE emp (
    id int,
    `name` varchar(32),
    sex char(1),
    birthday date,
    entry_time datetime,
    job varchar(32),
    salary double,
    `resume` text
) charset utf8 COLLATE utf8_bin ENGINE innodb;

INSERT INTO emp values(1, '小张', '男', '1995-11-21', '2023-1-1 13:13:13', '数据库管理员', 9000.0, '个人简介');

DESC emp; -- 查看表结构，可以查看列信息

ALTER TABLE emp ADD COLUMN image varchar(32) AFTER `resume`;

ALTER TABLE emp MODIFY COLUMN `job` varchar(60);

ALTER TABLE emp DROP COLUMN `sex`;

ALTER TABLE	emp CHARACTER SET utf8;

ALTER TABLE emp CHANGE COLUMN `name` `user_name` varchar(32); -- 修改列名称

ALTER TABLE emp RENAME TO `emp1`; -- 修改表名称

# 演示update语句
UPDATE emp SET salary = 5000; -- 如果没有带 WHERE 则会对所有记录进行修改

UPDATE emp SET salary = 3000 WHERE user_name = '小李';

UPDATE emp SET salary = salary + 1000, resume = 'new resume' WHERE user_name = '小张'; -- 修改多个字段可以用 , 隔开

# 使用 DELETE 语句仅能删除记录本身，不能删除表。如果需要删除表，则需要使用 DROP TABLE [table_name] 语句
DELETE FROM emp WHERE user_name = '小张2'; -- 删除记录，如果不带 where 则会删除所有记录

# SELECT 语句
CREATE TABLE `student` (
       id int NOT NULL DEFAULT 1,
       name varchar(20) NOT NULL DEFAULT '',
       chinese float NOT NULL DEFAULT 0.0,
       english float NOT NULL DEFAULT 0.0,
       math float NOT NULL DEFAULT 0.0
);

# LIKE 后面的 % 是占位用的
SELECT * FROM `student` WHERE (chinese + english + math) > 200 AND math < chinese AND `name` LIKE '%江';

SELECT * FROM `student` WHERE english BETWEEN 80 AND 90; -- between ... and ... 是闭区间

SELECT * FROM `student` WHERE math = 89 OR math = 90 OR math = 91;

# 上面的语句也可以写成这样
SELECT * FROM `student` WHERE math IN (89, 90, 91);

# order by 默认就是 aes 升序，可以省略
SELECT * FROM `student` ORDER BY math DESC, english;

SELECT `name`, (chinese + math + english) AS total_score FROM `student` ORDER BY total_score DESC;