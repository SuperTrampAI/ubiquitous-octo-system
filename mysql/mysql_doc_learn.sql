-- 版本和当前日期
SELECT VERSION(),CURRENT_DATE()

SELECT SIN(PI()/4), (4+1)*5;

-- 显示当前存在在数据库
SHOW DATABASES

-- 选择一个数据库
USE test

GRANT ALL ON menagerie.* TO 'test2'@'localhost';

-- 在shell中操作
-- 在登录时，指定数据库
myslq -h HOST -u USER -p menagerie

-- 查询当前使用的数据库
SELECT DATABASE();

-- 查看数据库表
SHOW TABLES;

-- 创建数据库
CREATE DATABASE menagerie;

-- 创建表
CREATE TABLE pet (NAME VARCHAR(20), OWNER VARCHAR(20),
       species VARCHAR(20), sex CHAR(1), birth DATE, death DATE);

-- 查看表结构
DESCRIBE pet;

-- 通过txt文件添加数据到表中
LOAD DATA LOCAL INFILE '/path/data.txt' INTO TABLE table_name LINES TERMINATED BY '\r\n';

-- insert 新增数据
INSERT INTO pet 
VALUES 
("Fluffy","Harold","cat",'f',"1993-02-04",null),
("Claws","Gwen","cat",'m',"1994-03-17",NULL),
("Buffy","Harold","dog",'f',"1989-05-13",null),
("Fang","Benny","dog",'m',"1990-08-27",null),
("Bowser","Diane","dog",'m',"1979-08-31","1995-07-29"),
("Chirpy","Gwen","bird",'f',"1998-09-11",null),
("Whistler","Gwen","bird",'',"1997-12-09",null),
("Slim","Benny","snake",'m',"1996-04-29",null);
	
-- 查询全部
SELECT * FROM 
-- select
-- 格式
SELECT what_to_select
FROM which_table
WHERE conditions_to_satisfy;

-- 清空表
DELETE FROM supertrampai_tbl;

-- 更新数据
UPDATE supertrampai_tbl SET supertrampai_author ="" WHERE NAME="";

-- 查询
SELECT * FROM pet WHERE NAME="Bowser";

-- 出生日期在1998-1-1以后的
SELECT * FROM pet WHERE birth>="1998-1-1";

-- m：雄性 f：雌性
-- 查询雌性dog
SELECT * FROM pet WHERE species="dog" AND sex='f';

-- 查找种类为snake 或者 bird
SELECT * FROM pet WHERE species="bird" OR species="snake"

-- and优先级高于or，同时使用这两个运算符时，最好使用括号明确的指出
SELECT * FROM pet WHERE (species = 'cat' AND sex = 'm')
       OR (species = 'dog' AND sex = 'f');
-- 查询全部：只显示owner列
SELECT OWNER FROM pet;

-- 使用distinct筛选数据，相同的只显示一个
SELECT DISTINCT OWNER FROM pet;

-- 使用关键字：binary对排序的列，区分大小写
SELECT * FROM pet ORDER BY BINARY col_name;

-- ase 升序  desc：降序

-- 对多个字段进行排序
SELECT * FROM pet ORDER BY col_name ,col_name2  DESC;

SELECT CURDATE();-- 年-月-日

-- 计算两个日期的年份之差,并按照名字进行排序
SELECT NAME, birth, CURDATE(),
TIMESTAMPDIFF(YEAR,birth,CURDATE()) AS age
FROM pet ORDER BY NAME;

-- 查询宠物的死亡年龄
SELECT NAME, birth, death,
       TIMESTAMPDIFF(YEAR,birth,death) AS age
       FROM pet WHERE death IS NOT NULL ORDER BY age;

-- 更改列的默认值
ALTER TABLE pet ALTER death SET DEFAULT NULL;

-- 把时间类型的列，截取出月份
SELECT NAME, birth, MONTH(birth) FROM pet;

-- 查找出当前月份生日的宠物  做一个定时任务，读取用户的出生年月字段，通过查询，发生消息
SELECT NAME,birth FROM pet WHERE MONTH(birth)=MONTH(NOW());

-- 当前日期的年份
SELECT YEAR(NOW());

-- 当前日期的月份
SELECT MONTH(NOW());

-- 当前日期的天
SELECT DAY(NOW());

SELECT NAME, birth FROM pet
       WHERE MONTH(birth) = MONTH(DATE_ADD(CURDATE(),INTERVAL 0 MONTH));

SELECT NAME, birth FROM pet
       WHERE MONTH(birth) = MOD(MONTH(CURDATE()), 12) + 1;
       
-- 
SELECT 1 IS NULL, 1 IS NOT NULL;

-- mysql 模糊查询
SELECT * FROM pet WHERE NAME LIKE "%fy";

SELECT * FROM pet WHERE NAME LIKE "______";

-- mysql从正则的使用： [asb] or  [a-z] or [0-9] ^已什么开头 $什么结尾

-- 使用pegexp_like 实现模糊查询 8.0版本才支持该函数
SELECT * FROM pet WHERE REGEXP_LIKE(NAME, '^b');

-- 区分大小写
SELECT * FROM pet WHERE REGEXP_LIKE(name, '^b' COLLATE utf8mb4_0900_as_cs);
SELECT * FROM pet WHERE REGEXP_LIKE(name, BINARY '^B');

-- 查询以fy结尾的name
SELECT * FROM pet WHERE REGEXP_LIKE(name, 'fy$');

-- 查找name中包含w的
SELECT * FROM pet WHERE REGEXP_LIKE(name, 'w');

-- 查找包含五个字符的名称
SELECT * FROM pet WHERE REGEXP_LIKE(name, '^.....$');

--  统计共计多少数据量
select count(1) FROM pet;

-- 分组统计:每个用户有几个宠物
select `OWNER`,count(1) from pet GROUP BY `OWNER`;

-- 每种性别的数量
select sex,count(1) from pet GROUP BY sex;

-- 每个物种的每种性别的数量
SELECT species, sex,COUNT(1) from pet GROUP BY species,sex;

SET sql_mode = 'ONLY_FULL_GROUP_BY';

-- 创建一个事件表
CREATE TABLE event (name VARCHAR(20), date DATE,
       type VARCHAR(15), remark VARCHAR(255));

-- 插入数据
INSERT into event(name,date,type,remark)
values("Fluffy","1995-05-15","litter","4 kittens, 3 female, 1 male"),
			("Buffy","1993-06-23","litter","5 puppies, 2 female, 3 male"),
			("Buffy","1994-06-19","litter","3 puppies, 3 female"),
			("Chirpy","1999-03-21","vet","needed beak straightened"),
			("Slim","1997-08-03","vet","broken rib"),
			("Bowser","1991-10-12","kennel",""),
			("Fang","1998-08-28","kennel",""),
			("Fang","1998-08-28","birthday","Gave him a new chew toy"),
			("Claws","1998-03-17","birthday","Gave him a new flea collar"),
			("Whistler","1998-12-09","birthday","First birthday");

SELECT p1.name, p1.sex, p2.name, p2.sex, p1.species
       FROM pet AS p1 INNER JOIN pet AS p2
         ON p1.species = p2.species
         AND p1.sex = 'f' AND p1.death IS NULL
         AND p2.sex = 'm' AND p2.death IS NULL;

-- 当前使用的数据库
select DATABASE();

-- 当前数据库的所有表
show TABLES;

-- 显示对应表结构 
DESCRIBE pet;

-- AUTO_INCREMENT  自动增长列

CREATE TABLE shop (
    article INT UNSIGNED  DEFAULT '0000' NOT NULL,
    dealer  CHAR(20)      DEFAULT ''     NOT NULL,
    price   DECIMAL(16,2) DEFAULT '0.00' NOT NULL,
    PRIMARY KEY(article, dealer)); -- 以两个字段为主键
INSERT INTO shop VALUES
    (1,'A',3.45),(1,'B',3.99),(2,'A',10.99),(3,'B',1.45),
    (3,'C',1.69),(3,'D',1.25),(4,'D',19.95);
		
select * from shop;

-- 列的最大值
select MAX(article) article from shop; 

-- 查询出prie最大的一条记录
-- 方案 1.
select * from shop where price = (select MAX(price) from shop)
-- 方案 2.
SELECT s1.article, s1.dealer, s1.price
FROM shop s1
LEFT JOIN shop s2 ON s1.price < s2.price
WHERE s2.article IS NULL;
-- 方案 3.SELECT article, dealer, price
FROM shop
ORDER BY price DESC
LIMIT 1;

-- --------------------------------
SELECT article, MAX(price) AS price
FROM   shop
GROUP BY article
ORDER BY article;

-- 使用自定义变量
SELECT @min_price:=MIN(price),@max_price:=MAX(price) FROM shop;

-- 使用自定义变量的值!!!
select @min_price;

SELECT * FROM shop WHERE price=@min_price OR price=@max_price;

-- mysql 中定义外键约束

CREATE TABLE person (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name CHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE shirt (
    id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    style ENUM('t-shirt', 'polo', 'dress') NOT NULL,
    color ENUM('red', 'blue', 'orange', 'white', 'black') NOT NULL,
    owner SMALLINT UNSIGNED NOT NULL REFERENCES person(id),
    PRIMARY KEY (id)
);

INSERT INTO person VALUES (NULL, 'Antonio Paz');

SELECT @last := LAST_INSERT_ID();

INSERT INTO shirt VALUES
(NULL, 'polo', 'blue', @last),
(NULL, 'dress', 'white', @last),
(NULL, 't-shirt', 'blue', @last);

INSERT INTO person VALUES (NULL, 'Lilliana Angelovska');

SELECT @last := LAST_INSERT_ID();

INSERT INTO shirt VALUES
(NULL, 'dress', 'orange', @last),
(NULL, 'polo', 'red', @last),
(NULL, 'dress', 'blue', @last),
(NULL, 't-shirt', 'white', @last);

SELECT * FROM person;
SELECT * FROM shirt;

SELECT s.* FROM person p INNER JOIN shirt s
   ON s.owner = p.id
 WHERE p.name LIKE 'Lilliana%'
   AND s.color <> 'white';

-- -------------------------

CREATE TABLE t1 (year YEAR(4), month INT UNSIGNED,
             day INT UNSIGNED);
INSERT INTO t1 VALUES(2000,1,1),(2000,1,20),(2000,1,30),(2000,2,2),
            (2000,2,23),(2000,2,23);
-- 查找某一年某一月的某一天有几条数据 如果一条有两条数据，这只计入一条
SELECT year,month,BIT_COUNT(BIT_OR(1<<day)) AS days FROM t1
       GROUP BY year,month;

show GRANTS;

-- 数据类型   comment 中的内容依次为： 	存储（字节）	最小值签名	最小值无符号	最大值签名	最大值无符号
create table data_type_table(
	column1 TINYINT NOT NULL comment "1	-128	0	127	255",
	column2 SMALLINT NOT NULL comment "2	-32768	0	32767	65535",
	column3 MEDIUMINT NOT NULL comment "-8388608	0	8388607	16777215",
	column4 INT NOT NULL comment "-2147483648	0	2147483647	4294967295",
	column5 BIGINT NOT NULL comment "-2 63	0	2 63-1	2 64-1",
	column6 DECIMAL(5,2) NOT NULL comment "货币数据 5是精度， 2是规模  精度表示为值存储的有效位数，刻度表示小数点后可存储的位数。",
	column7 NUMERIC NOT NULL comment "货币数据",
	column8 FLOAT NOT NULL comment "单精度值使用四个字节，。",
	column9 DOUBLE NOT NULL comment "对于双精度值使用八个字节",
	column10 date	NOT NULL comment "YYYY-MM-DD  支持的范围是 '1000-01-01'到 '9999-12-31' ",
	column11 DATETIME NOT NULL comment "YYYY-MM-DD hh:mm:ss '1000-01-01 00:00:00'到'9999-12-31 23:59:59'。",
	column12 TIMESTAMP NOT NULL comment "'1970-01-01 00:00:01'UTC到'2038-01-19 03:14:07'UTC ",
	column13 TIME NOT NULL comment "hh:mm:ss '-838:59:59'到 '838:59:59'",
	column14 YEAR  NOT NULL comment "",
	column15 YEAR(4) NOT NULL comment "",
	column16 CHAR NOT NULL comment "",
	column17 BINARY NOT NULL comment "",
	column17 VARBINARY NOT NULL comment "",
	column17 BLOB NOT NULL comment "",
	column17 TEXT NOT NULL comment "",
	column17 ENUM('x-small', 'small', 'medium', 'large', 'x-large') NOT NULL comment "",
	column17 SET('a', 'b', 'c', 'd') NOT NULL comment "",
)

-- 关于TIMESTAMP和DATETIME的自动初始化和更新

-- 使用DEFAULT CURRENT_TIMESTAMP和 ON UPDATE CURRENT_TIMESTAMP，列具有其默认值的当前时间戳，并自动更新为当前时间戳。
CREATE TABLE t1 (
  ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  dt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 使用DEFAULT子句但没有ON UPDATE CURRENT_TIMESTAMP子句，该列具有给定的默认值，并且不会自动更新为当前时间戳。
-- 默认值取决于 DEFAULT子句是指定 CURRENT_TIMESTAMP还是常量值。使用CURRENT_TIMESTAMP，默认为当前时间戳。
CREATE TABLE t1 (
  ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  dt DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 默认值
CREATE TABLE t1 (
  -- literal defaults
  i INT         DEFAULT 0,
  c VARCHAR(10) DEFAULT '',
  -- expression defaults
  f FLOAT       DEFAULT (RAND() * RAND()),
  b BINARY(16)  DEFAULT (UUID_TO_BIN(UUID())),
  d DATE        DEFAULT (CURRENT_DATE + INTERVAL 1 YEAR),
  p POINT       DEFAULT (Point(0,0)),
  j JSON        DEFAULT (JSON_ARRAY())
);






















































