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

# count 函数
SELECT count(*) FROM `student`;

SELECT count(*) FROM `student` WHERE english > 90;

SELECT count(*) FROM `student` WHERE (chinese + math + english) > 250;

# sum 函数
SELECT sum(math), sum(chinese) FROM `student`;

SELECT (sum(chinese) / count(chinese)) AS chinese_average_score FROM `student`;

# avg 函数
SELECT avg(chinese), avg(math), avg(english) FROM `student`;

SELECT avg(chinese + math + english) FROM `student`;

# max min
SELECT max(chinese), max(math), max(english) FROM `student`;

SELECT min(chinese), min(math), min(english) FROM `student`;


create table employee(
     empno mediumint  unsigned not null default 0,
     ename varchar(20) not null default '',
     job varchar(9) not null default '',
     mgr mediumint unsigned,
     hiredate date not null,
     sal decimal(7,2) not null,
     comm decimal(7,2),
     deptno mediumint unsigned not null default 0
);

INSERT INTO employee VALUES(7369,'SMITH','CLERK',7902,'1990-12-17',800.00,NULL,20),
       (7499,'ALLEN','SALESMAN',7698,'1991-2-20',1600.00,300.00,30),
       (7521,'WARD','SALESMAN',7968,'1991-2-22',1250.00,500.00,30),
       (7566,'JONES','MANAGER',7839,'1991-4-2',2975.00,NULL,20),
       (7654,'MARTIN','SALESMAN',7968,'1991-9-28',1250.00,1400.00,30),
       (7698,'BLAKE','MANAGER',7839,'1991-5-1',2850.00,NULL,30),
       (7782,'CLARK','MANAGER',7839,'1991-6-9',2450.00,NULL,10),
       (7788,'SCOTT','ANALYST',7566,'1991-4-19',3000.00,NULL,20),
       (7839,'KING','PRESIDENT',NULL,'1991-11-17',5000.00,NULL,10),
       (7844,'TURNER','SALESMAN',7698,'1991-9-8',1500.00,NULL,30),
       (7900,'JAMES','CLERK',7698,'1991-12-3',950.00,NULL,30),
       (7902,'FORD','ANALYST',7566,'1991-12-3',3000.00,NULL,20),
       (7934,'MILLER','CLERK',7782,'1991-1-23',1300.00,NULL,10);


CREATE TABLE dept (
      deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
      dname varchar(20) NOT NULL DEFAULT '',
      loc varchar(13) NOT NULL DEFAULT ''
);

INSERT INTO dept values(10, 'ACCOUNTING', 'NEW YORK'),
       (20, 'RESEARCH', 'DALLAS'),
       (30, 'SALES', 'CHICAGO'),
       (40, 'OPERATIONS', 'BOSTON');

# 工资级别表
CREATE TABLE salgrade (
      grade MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
      low_salary decimal(17, 2) NOT NULL,
      high_salary decimal(17, 2) NOT NULL
);

INSERT INTO salgrade values(1, 700, 1200);
INSERT INTO salgrade values(2, 1201, 1400);
INSERT INTO salgrade values(3, 1401, 2000);
INSERT INTO salgrade values(4, 2001, 3000);
INSERT INTO salgrade values(5, 3001, 999);

ALTER TABLE salgrade RENAME TO salary_grade;

# ALTER TABLE salary_grade CHANGE COLUMN `grade1` `grade` MEDIUMINT;

# 按照部门来分组查询平均工资和最低工资，按照部门分组的意思是汇总统计employee表中deptno相同的记录
SELECT avg(sal), min(sal), deptno FROM `employee` GROUP BY deptno;

# 显示每个部门的每种岗位的平均工资和最低工资
SELECT avg(sal), min(sal), deptno, job FROM `employee` GROUP BY deptno, job;

# 显示平均工资低于2000的部门号和它的平均工资（使用别名）
SELECT deptno, avg(sal) AS avg_sal FROM `employee` GROUP BY deptno HAVING avg_sal < 2000;

# 字符串函数
# CHARSET(str) 返回字串字符集
SELECT charset(ename) FROM `employee`;

# CONCAT(string2, [, ...]) 连接字符串
SELECT concat(ename, '-', job) FROM `employee`;

# INSTR(string, substring) 返回substring在string中出现的位置(字符串中的第一个在字符串中的位置是1)，没有返回0（是否区分大小写跟随表的校对规则走）
SELECT instr(ename, 'a') FROM `employee`;
SELECT instr(ename, 'A') FROM `employee`;

# UCASE 转换成大写 LCASE 转换成小写
SELECT lcase(ename) FROM `employee`;

# LEFT(string2, length) RIGHT(string2, length) 从string2中的左/右边取length个字符
SELECT left(ename, 2) FROM `employee`;

# REPLACR（str, search_str, replace_str） 在str中永replace_str替换search_str
SELECT REPLACE(ename, 'LL', '0009') FROM `employee`;

# STRCMP(string1, string2) 逐字符比较两个字符串，如果这两个字符串相等返回0，如果第一个参数是根据当前的排序小于第二个参数顺序返回-1，否则返回1。


# SUBSTRING(str, position [, length]) 从str的position开始（position是从1开始计算的），取length个字符
SELECT substring(ename, 2, 2) FROM `employee`;

# LTRIM(string2) RTRIM(string2) 去除字符串左端或右端的空格 TRIM(string2) 去除左右两边的空格
SELECT ltrim(ename) FROM `employee`;

# DUAL 是亚元表，系统表，可以作为测试表使用
SELECT instr('xiaozhang', 'o') FROM DUAL; -- 4

# LENGTH(string) 返回字符串的长度(按照字节)
SELECT length('小张') FROM DUAL; -- 6 按照字节返回

# 以首字母小写的方式显示所有员工employee表的姓名
SELECT concat(lcase(LEFT(ename, 1)), substring(ename, 2)) FROM `employee`;

# 演示数学相关函数
# ABS(num) 绝对值
SELECT abs(-1) FROM DUAL;

# BIN(decimal_number) 十进制转二进制
SELECT bin(12) FROM DUAL;

# CEiLING(number2) 向上取整 FLOOR(number2) 向下取整
SELECT ceiling(0.1) FROM DUAL;

# CONV(number2, from_base, to_base) 进制转换
SELECT conv(1100, 2, 10) FROM DUAL;
SELECT conv(12, 10, 2) FROM DUAL;

# FORMAT(number, decimal_places) 保留小数位数(四舍五入)
SELECT format(12.12345, 3) FROM DUAL;

# HEX(decimal_number) 将一个字符串或数字转换为十六进制格式的字符串
# unhex() 把十六进制字符串转换为原来的格式
SELECT hex('12a') FROM DUAL; -- 313261
SELECT unhex(313261) FROM DUAL; -- '12a'

# LEAST(number1, number2, [,...]) 求最小值
SELECT least(0, -1, 2, -2) FROM DUAL;

# MOD(numerator(分子), denominator(分母)) 求余
SELECT mod(12, 5) FROM DUAL; -- 2

# RAND([seed]) 返回随机数，范围为[0, 1]，使用seed参数后，每一次返回的随机数都是确定的
SELECT rand() FROM DUAL; -- 每执行一次都不一样
SELECT rand(3) FROM DUAL; -- 就会一直是指定的随机数

# 时间日期相关的函数
# CURRENT_DATE() 当前日期 CURRENT_TIME() 当前时间 CURRENT_TIMESTAMP() 当前时间戳
SELECT current_date() FROM DUAL;
SELECT current_time() FROM DUAL;
SELECT current_timestamp() FROM DUAL;

# DATE(datetime) 返回datetime的日期部分
SELECT date('2024-04-14 16:41:23') FROM DUAL;

# DATE_ADD(date2, INTERVAL d_value d_type) 在date2中加上日期或时间
# DATE_SUB(date2, INTERVAL d_value d_type) 在date2中减去上日期或时间 可以是 date, datetime 和 timestamp
SELECT date_add(now(), interval 10 day) from dual;

# DATEDIFF(date1, date2) 两个日期差（结果是天）
SELECT datediff('2024-04-14', '2024-03-14') FROM DUAL;

# TIMEDIFF(date1, date2) 两个时间差（多少小时多少分钟多少秒）
SELECT timediff('2024-04-14 16:41:23', '2024-03-14 16:41:23') FROM DUAL;

# NOW() 当前时间 CURRENT_TIMESTAMP() 作用与NOW()一致
# now()函数获得的是语句开始执行时的时间，而sysdate()函数是这个函数执行时候的时间
SELECT now() FROM DUAL;

-- 创建测试表
CREATE TABLE `test_msg` (
    id int,
    content varchar(10),
    send_time datetime
);

INSERT INTO `test_msg` values(1, '北京新闻', current_timestamp());
INSERT INTO `test_msg` values(2, '上海新闻', current_timestamp());
INSERT INTO `test_msg` values(3, '广州新闻', current_timestamp());
INSERT INTO `test_msg` values(4, '成都新闻', current_timestamp());

# 显示所有留言信息，发布日期只显示日期，不用显示时间
SELECT id, content, date(send_time) FROM `test_msg`;

# 查询在10分钟内发布的帖子
SELECT * FROM `test_msg`
    WHERE date_add(send_time, INTERVAL 10 MINUTE) >= now();
SELECT * FROM `test_msg`
    WHERE date_sub(now(), interval 10 minute) <= send_time;

# 2011-11-11 和 2000-1-1 相差多少天
SELECT datediff('2011-11-11', '2000-1-1') FROM DUAL;

# 一个1980-1-1出生的人如果能够活到80岁，那么它还可以或多少天？
SELECT datediff(date_add('1980-1-1', interval 80 year), now()) from dual;

# YEAR|MONTH|DAY (datetime)
SELECT year(now()) FROM DUAL;
SELECT month(now()) FROM DUAL;
SELECT day(now()) FROM DUAL;

# unix_timestamp([datetime]) 返回的是1970-1-1到现在(或datetime)的秒数
SELECT unix_timestamp() FROM DUAL;

# FROM_UNIXTIME() 可以把一个unix_timestamp秒数，转成指定格式的日期
# %Y-%m-%d 是规定好的，表示 年月日
SELECT from_unixtime(1713092171, '%Y-%m-%d') FROM DUAL;
SELECT from_unixtime(1713092171, '%Y-%m-d %H:%i:%s') FROM DUAL;

# 演示加密函数和系统函数
# USER() 查询用户 可以查看登录到mysql的有哪些用户，登录的ip
SELECT user() FROM DUAL;

# 数据库名称
SELECT DATABASE() FROM DUAL;

# password(str) 加密（mysql存储的密码就使用password加密的）
SELECT password('root') FROM DUAL;
SELECT * FROM mysql.USER;

# MD5(str) 为字符串计算出一个MD5 32的字符串，用于用户密码等的加密
SELECT MD5('xiaozhang') FROM DUAL;

CREATE TABLE test_user(
      id int,
      `name` varchar(32) NOT NULL DEFAULT '',
      pwd char(32) NOT NULL DEFAULT ''
);

INSERT INTO `test_user` values(1, 'xiaozhang', md5('xiaozhang'));
INSERT INTO `test_user` values(2, '张三', md5('123aaawe1'));
INSERT INTO `test_user` values(3, '李四', md5('8888aaa'));

SELECT * FROM `test_user` WHERE `name` = '张三' AND pwd = md5('123aaawe1');

# 演示流程控制语句
# IF(expr1, expr2, expr3) 如果expr1为true，则返回expr2，否则返回expr3
SELECT if(TRUE, '北京', '上海') FROM DUAL;

# IFNULL(expr1, expr2) 如果expr1不为null则返回expr1，否则返回expr2
SELECT ifnull(NULL, 'xiaozhang') FROM DUAL;
SELECT ifnull('NULL', 'xiaozhang') FROM DUAL;

# case when expr1 then expr2 when expr3 then expr4 else expr5
SELECT CASE
   WHEN TRUE THEN '1'
   WHEN FALSE THEN '2'
   ELSE '3'
   END;

# 查询employee表，如果comm是null，则显示0.0   判断是否为null需要使用 is. eg: if(comm is null, expr1, expr2)
SELECT ename, if(comm IS NULL, 0.0, comm) AS 'comm' FROM `employee`;

# 如果 employee 表的job是 CLERK 则显示 职员，如果是 MANAGER 则显示经理，如果是 SALESMAN 则显示 销售人员，其他正常显示
SELECT ename, (CASE
       WHEN job = 'CLERK' THEN '职员'
       WHEN job = 'MANAGER' THEN '经理'
       WHEN job = 'SALESMAN' THEN '销售人员'
       ELSE job
    END) AS 'job'
FROM `employee`;

# 查询1991-06-01后入职的员工 mysql 中日期类型是可以直接比较大小的，注意格式
SELECT * FROM `employee` WHERE hiredate > '1991-06-01';

# like 模糊查询，% 表示0到多个任意字符 _ 表示单个任意字符
# 如何显示首字符为s的员工姓名和工资
SELECT ename, sal FROM `employee` WHERE ename LIKE 'S%' OR ename LIKE 's%';

UPDATE `employee` SET ename = 'SMITH' WHERE ename = 'sMITH';

SELECT ename, sal FROM `employee` WHERE ename LIKE '__O%';

SELECT ename FROM `employee` WHERE mgr IS NULL;

DESC `employee`;

SELECT * FROM `employee` ORDER BY sal desc;

SELECT * FROM `employee` ORDER BY deptno DESC, sal;

# 分页查询 select ... limit start, rows; 从start+1行开始取，取rows行
# 按雇员id号升序取出，每页显示3条记录，分别显示第1页，第2页，第3页
SELECT * FROM `employee` ORDER BY empno LIMIT 0, 3;
SELECT * FROM `employee` ORDER BY empno LIMIT 3, 3;
SELECT * FROM `employee` ORDER BY empno LIMIT 6, 3;
SELECT * FROM `employee` ORDER BY empno DESC LIMIT 10, 5;
SELECT * FROM `employee` ORDER BY empno DESC LIMIT 20, 5;

# 显示每种岗位的雇员总数、平均工资
SELECT job, count(*) AS employee_count, avg(sal) FROM `employee` GROUP BY job;

# 显示雇员总数，以及获得补助的雇员总数  count(列) 列如果为null就不会被统计
SELECT count(*) AS total, count(comm) FROM `employee`;
SELECT count(*) AS total, count(IF(comm IS NULL, NULL, comm)) FROM `employee`;
# 没有获得补助的人数
SELECT count(*) AS total, count(IF(comm IS NULL, 1, null)) FROM `employee`;
SELECT count(*) AS total, count(*) - count(comm) FROM `employee`;

# 显示管理者的总人数 count([distinct] 列) 也可以去重
SELECT count(DISTINCT mgr) FROM `employee`;

# 显示工资的最大差额
SELECT max(sal) - min(sal) FROM `employee`;

# 统计各个部门的平均工资，并且是大于1000的，并且按照平均工资从高到低排序，取出前两行记录
SELECT deptno, avg(sal) AS avg_sal FROM `employee`
    GROUP BY deptno
    HAVING avg_sal > 1000
    ORDER BY avg_sal DESC
    LIMIT 0, 2;

# 多表查询
# 显示雇员名称，雇员工资及所在部门的名称(笛卡尔集) 当查询的字段在多张表中都存在时，需要带上表的名称
SELECT ename, sal, dname, employee.deptno FROM `employee`, `dept`
    WHERE employee.deptno = dept.deptno;

# 显示部门号为10的部门名，员工名和工资
SELECT dname, ename, sal FROM `employee`, `dept`
    WHERE dept.deptno = employee.deptno AND dept.deptno = 10;

# 显示各个员工的姓名，工资，及其工资的级别
SELECT ename, sal, grade FROM `employee`, `salary_grade`
    WHERE sal BETWEEN low_salary AND high_salary;

# 显示雇员名称，雇员工资及所在部门的名字，并按部门号降序排序
SELECT ename, sal, dname, dept.deptno FROM `employee`, `dept`
    WHERE employee.deptno = dept.deptno
    ORDER BY dept.deptno DESC;

# 显示公司员工和他上级的名字
SELECT emp1.ename AS emp_name, emp2.ename AS mgr_name
    FROM `employee` emp1, `employee` emp2
    WHERE emp1.mgr = emp2.empno;

# 显示与SMITH同一部门的所有员工 注意直接等于子查询的语句时，需要保证查出来的值只有一个
SELECT * FROM `employee`
    WHERE deptno = (
        SELECT deptno FROM `employee` WHERE ename = 'SMITH'
    );

# 查询和部门10的工作相同的雇员的名字、岗位、工资、部门号、但是不含10号部门自己的雇员
SELECT DISTINCT job FROM `employee` WHERE deptno = 10;
    SELECT ename, job, sal, deptno FROM `employee`
    WHERE job IN (
        SELECT DISTINCT job FROM `employee` WHERE deptno = 10
    ) AND deptno != 10;

INSERT INTO `employee` values(7369,'SMITH','CLERK',7902,'1990-12-17',800.00,NULL,10);
DELETE FROM `employee` WHERE ename = 'SMITH' AND deptno = 10;

# 查询每一个工作类别中工资最高的人的名称及其工资
SELECT job, ename, sal FROM `employee`
    WHERE sal IN (
        SELECT max(sal) FROM `employee` GROUP BY job
    );

# 显示工资比部门号为30的部门的所有员工的工资高的员工的姓名
SELECT ename, sal, deptno FROM `employee`
    WHERE sal > (
        SELECT max(sal) FROM `employee` WHERE deptno = 30
    );
SELECT ename, sal, deptno FROM `employee`
    WHERE sal > all(SELECT sal FROM `employee` WHERE deptno = 30);

# 显示工资比部门30的其中一个员工的工资高的员工的姓名、工资和部门号
SELECT ename, sal, deptno FROM `employee`
    WHERE sal > any(
        SELECT sal FROM `employee` WHERE deptno = 30
    );
SELECT ename, sal, deptno FROM `employee`
    WHERE sal > (
        SELECT min(sal) FROM `employee` WHERE deptno = 30
    );

# 多列子查询 指查询返回多个数据的子查询语句
# 查询ALLEN的部门和岗位完全相同的所有雇员（不包含ALLEN本人）
SELECT ename, deptno, job FROM `employee`
WHERE (deptno, job) = (
    SELECT deptno, job FROM `employee` WHERE ename = 'ALLEN'
) AND ename != 'ALLEN';

# 查找每个部门工资高于本部门平均工资的人的资料
SELECT ename, sal, temp.avg_sal, temp.deptno FROM `employee`, (
    SELECT deptno, avg(sal) AS avg_sal FROM `employee` GROUP BY deptno
) temp
WHERE employee.deptno = temp.deptno AND employee.sal > temp.avg_sal;

# 查找每个部门工资最高的人的资料
SELECT * FROM `employee`, (
    SELECT deptno, max(sal) AS max_sal FROM `employee` GROUP BY deptno
) temp
WHERE employee.deptno = temp.deptno AND employee.sal = temp.max_sal;

# 显示每个部门的信息(包括：部门名，编号，地址)和人员数量
# 表名.* 表示将表的所有字段都显示出来
SELECT dept.*, temp.count_dept_emp FROM dept, (
    SELECT deptno, count(*) AS count_dept_emp FROM `employee` GROUP BY deptno
) temp
WHERE dept.deptno = temp.deptno;

# 表的复制
CREATE TABLE my_table01 (
    id int,
    `name` varchar(32),
    sal double,
    job varchar(32),
    deptno int
);

DESC my_table01;

# 把employee表的数据复制到my_table01
INSERT INTO my_table01 (id, `name`, sal, job, deptno)
SELECT empno, ename, sal, job, deptno FROM `employee`;

# 自我复制
INSERT INTO my_table01 SELECT * FROM my_table01;

# 删除掉一张表中的重复记录 把该表复制到另一张表中（distinct），删除该表（drop），再把复制到的表重命名为该表即可

# 创建一张表使用指定表的列结构
CREATE TABLE my_table02 LIKE employee;

# 合并查询 union all 和 union
# union all 该操作符用于取得两个结果的并集，当使用该操作符时，不会取消重复行
# union 操作符会去掉重复行
SELECT ename, job, sal FROM `employee` WHERE sal > 2500 UNION ALL
SELECT ename, job, sal FROM `employee` WHERE job = 'MANAGER';

# 列出部门名称和这些部门的员工名称和工作，同时要求显示出那些没有员工的部门
SELECT dept.deptno, dname, ename, job FROM	`employee` RIGHT JOIN `dept`
                                                                    ON dept.deptno = employee.deptno
ORDER BY dept.deptno;
SELECT dept.deptno, dname, ename, job FROM `dept` LEFT JOIN `employee`
                                                            ON dept.deptno = employee.deptno
ORDER BY dept.deptno;

# 演示外连接
CREATE TABLE `join_stu` (id int, `name` varchar(32));
INSERT INTO `join_stu` VALUES (1, 'jack'), (2, 'tom'), (3, 'kity'), (4, 'nono');

CREATE TABLE `join_exam` (id int, grade int);
INSERT INTO `join_exam` VALUES (1, 56), (2, 76), (11, 8);

# 使用左连接（显示所有人的成绩，如果没有成绩，也要显示该人的姓名和id号，成绩显示为空）
SELECT join_stu.id, `name`, grade FROM `join_stu`, `join_exam`
WHERE join_stu.id = join_exam.id;
# 使用左外连接
SELECT join_stu.id, `name`, grade FROM `join_stu` LEFT JOIN `join_exam`
                                                            ON join_stu.id = join_exam.id;

# 右连接（显示所有成绩，如果没有名字匹配，显示空）
SELECT join_stu.id, `name`, grade FROM `join_stu` RIGHT JOIN `join_exam`
                                                             ON join_stu.id = join_exam.id;


# 约束
# primary key
CREATE TABLE `test01` (id int PRIMARY KEY, `name` varchar(32), email varchar(32));
INSERT INTO `test01` VALUES (1, 'xiaozhang', 'xiaozhang@163.com');
INSERT INTO `test01` VALUES (2, '李四', '李四@163.com');
INSERT INTO `test01` VALUES (1, '王五', '王五@163.com'); -- 报错，主键不能重复

CREATE TABLE `test02` (
      id int,
      `name` varchar(32),
      email varchar(32),
      PRIMARY KEY (id, name) -- 这就是复合主键(两个列合起来才是主键)，添加数据时只有这两个值都相同才会报错
);
INSERT INTO `test02` VALUES (1, 'xiaozhang', 'xiaozhang@163.com');
INSERT INTO `test02` VALUES (2, '李四', '李四@163.com');
INSERT INTO `test02` VALUES (1, '王五', '王五@163.com'); -- 不报错，因为是id、name两个字段组合起来为主键
INSERT INTO `test02` VALUES (1, '王五', '王五11@163.com'); -- 报错
DESC `test02`;

# 修改 test02.name 为 unique
ALTER TABLE test02 MODIFY COLUMN `name` varchar(32) UNIQUE;
INSERT INTO `test02` VALUES (3, 'xiaozhang', 'zz@163.com');

# 演示外键
# 创建主表
CREATE TABLE foreign_key_class (
       id int PRIMARY KEY,
       `name` varchar(32) NOT NULL DEFAULT ''
);

# 创建从表
CREATE TABLE foreign_key_stu (
         id int PRIMARY KEY,
         `name` varchar(32) NOT NULL DEFAULT '',
         class_id int,
         -- 下面指定外键关系
         FOREIGN KEY (class_id) REFERENCES foreign_key_class(id)
);

# 插入数据
INSERT INTO foreign_key_class VALUES (100, 'java'), (200, 'web');
INSERT INTO foreign_key_stu VALUES (1, 'tom', 100);
INSERT INTO foreign_key_stu VALUES (2, 'jack', 200);
INSERT INTO foreign_key_stu VALUES (3, 'jery', 300); -- 插入失败，外键指向的主表中没有300号班级
# 修改已经插入的外键的值为主键字段没有出现过的值 受外键约束不能成功
UPDATE foreign_key_stu SET class_id = 300
WHERE id = 2;

# 演示check mysql5.7 目前还不支持check，只做语法校验，但不会生效
CREATE TABLE check_table (
         id int PRIMARY KEY,
         `name` varchar(32),
         sex varchar(6) CHECK (sex IN ('man', 'woman')),
         sal double CHECK (sal > 1000 AND sal < 2000)
);

INSERT INTO `check_table` VALUES (1, 'xiaozhang', 'mid', 1); -- check未生效，仍然能够添加成功

# 新建商店数据库相关的商品goods、客户customer和购买purchase三张表
CREATE TABLE `shop_customer` (
         `customer_id` int PRIMARY KEY,
         `name` varchar(32) NOT NULL DEFAULT '',
         `address` varchar(60),
         `email` varchar(32) UNIQUE,
         `sex` char(1) CHECK (`sex` IN ('男', '女')),
         `card_id` char(18) UNIQUE
);
ALTER TABLE `shop_customer` MODIFY COLUMN
    `sex` enum('男', '女') NOT NULL; -- 使用枚举实现（mysql中生效）
SHOW CREATE TABLE `shop_customer`;

CREATE TABLE `shop_goods` (
          `goods_id` int PRIMARY KEY,
          `goods_name` varchar(60) NOT NULL DEFAULT '',
          `unitprice` double CHECK (`unitprice` BETWEEN 1.0 AND 9999.99),
          `category` varchar(32),
          `provider` varchar(32) NOT NULL DEFAULT ''
);
ALTER TABLE `shop_goods` MODIFY COLUMN
    `unitprice` decimal(10, 2) NOT NULL DEFAULT 1.0;
ALTER TABLE `shop_goods` MODIFY COLUMN
    `category` int NOT NULL DEFAULT 0; -- 设计的表字段不要为空，都加上not NULL DEFAULT
SHOW CREATE TABLE `shop_goods`;

CREATE TABLE `shop_purchase` (
         `order_id` int PRIMARY KEY,
         `customer_id` int NOT NULL DEFAULT 0,
         `goods_id` int NOT NULL DEFAULT 0,
         `nums` int UNSIGNED NOT NULL DEFAULT 0,
         CONSTRAINT `purchase_customer` FOREIGN KEY (`customer_id`) REFERENCES `shop_customer` (`customer_id`),
         CONSTRAINT `purchase_goods` FOREIGN KEY (`goods_id`) REFERENCES `shop_goods` (`goods_id`)
);

DESC `shop_purchase`;
SHOW CREATE TABLE `shop_goods`; -- 查看表的创建语句，可以知道表的约束情况

# 自增长
CREATE TABLE `test03` (
          `id` int PRIMARY KEY AUTO_INCREMENT,
          `name` varchar(32) NOT NULL DEFAULT ''
);
INSERT INTO `test03` VALUES (NULL, 'tom');
INSERT INTO `test03` VALUES (NULL, 'jack');
INSERT INTO `test03` (`name`) VALUES ('jery');

# 索引
CREATE TABLE `index_table01` (
     `id` int,
     `name` varchar(32)
);
# 添加普通索引
CREATE INDEX `index01` ON `index_table01` (`id`);
ALTER TABLE `index_table01` ADD INDEX `index02` (`name`);

# 删除索引
DROP INDEX `index01` ON `index_table01`;
DROP INDEX `index02` ON `index_table01`;

# 添加unique索引
CREATE UNIQUE INDEX `unique_index01` ON `index_table01` (`name`);

# 添加主键索引
ALTER TABLE `index_table01` ADD PRIMARY KEY (`id`);

# 删除主键索引
ALTER TABLE `index_table01` DROP PRIMARY KEY;

# 查询表的索引 show keys from table_name show index from table_name 都可以查询表的索引
SHOW KEYS FROM `index_table01`;
SHOW INDEX FROM `index_table01`;

SHOW CREATE TABLE `index_table01`;

# 事务
# 1、创建表
CREATE TABLE `transaction_table` (
     `id` int PRIMARY KEY,
     `name` varchar(32)
);

# 2、开启事务
START TRANSACTION;

# 3、设置保存点
SAVEPOINT a;

# 4、执行dml语句
INSERT INTO `transaction_table` VALUES (100, 'tom');

SAVEPOINT b;

INSERT INTO `transaction_table` VALUES (200, 'jack');

# 5、回退到b
ROLLBACK TO b;
# 也可以继续回退到a, 如果不带任何的保存点则回退到事务开始时的状态
ROLLBACK TO a;
ROLLBACK;

# 结束事务
COMMIT;

SELECT * FROM `transaction_table`;

# 查看当前的会话隔离级别
SELECT @@transaction_isolation;

# 查看系统给当前的隔离级别
SELECT @@global.transaction_isolation;

# 设置会话的隔离级别为 READ UNCOMMITED 读未提交
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

# 查看存储引擎
SHOW engines;

# 修改表的存储引擎
ALTER TABLE `transaction_table` ENGINE = INNODB;