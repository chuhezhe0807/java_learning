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
