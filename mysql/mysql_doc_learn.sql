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
("Fluffy","Harold","cat",'f',"1993-02-04",""),
("Claws","Gwen","cat",'m',"1994-03-17",""),
("Buffy","Harold","dog",'f',"1989-05-13",""),
("Fang","Benny","dog",'m',"1990-08-27",""),
("Bowser","Diane","dog",'m',"1979-08-31","1995-07-29"),
("Chirpy","Gwen","bird",'f',"1998-09-11",""),
("Whistler","Gwen","bird",'',"1997-12-09",""),
("Slim","Benny","snake",'m',"1996-04-29","");
	
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