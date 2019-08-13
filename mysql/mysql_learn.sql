-- mysql dabatase

-- 新建具有select insert update权限的user
INSERT INTO USER 
   (HOST, USER, PASSWORD, 
   select_priv, insert_priv, update_priv) 
   VALUES ('localhost', 'guest', 
   PASSWORD('guest'), 'Y', 'Y', 'Y');
   
-- 新建没有任何权限的用户
INSERT INTO USER 
   (HOST, USER, PASSWORD) 
   VALUES ('localhost', 'test1', 
   PASSWORD('test1'));

-- 新建用户以后，使用如下命令重启，才能使用新建的用户登录
FLUSH PRIVILEGES;

-- 查看tables_priv可以查看用户拥有哪些表的操作权限。
SELECT *FROM tables_priv;

SELECT HOST,USER,PASSWORD FROM USER;

-- 新建用户
-- 模板：grant [权限1，权限2...] on 某库.某表 to 新用户名@'主机名/IP地址' identified by '密码';
GRANT ALL ON *.* TO user1@'localhost' IDENTIFIED BY '123456';

-- 使用delete删除

-- 给hehe用户赋予操作test库的所有权限
GRANT ALL ON test.* TO user1@'localhost' IDENTIFIED BY '123456';

-- 定义给用户hehe操作test库的goods表的insert，select，update的权限
GRANT INSERT,SELECT,UPDATE ON test.table TO user1@'localhost' IDENTIFIED BY '123456';

-- 回收用户的库级和表级权限
-- 模板：revoke [权限1，权限2...] on 某库.某表 from 用户名@'主机名/IP';
-- 回收用户hehe对test库的所有表的update的权限
REVOKE UPDATE ON test.* FROM user1@'localhost';

-- --------------------------------------------------------------------------

-- 在cmd新建数据库
mysqladmin -u root -p CREATE supertrampai

-- 在cmd删除数据库
mysqladmin -u root -p DROP supertrampai;

-- 使用数据库
USE supertrampai;

-- 创建表
CREATE TABLE supertrampai_tbl(
   supertrampai_id INT NOT NULL AUTO_INCREMENT,
   supertrampai_title VARCHAR(100) NOT NULL,
   supertrampai_author VARCHAR(40) NOT NULL,
   submission_date DATE,
   PRIMARY KEY ( supertrampai_id )
);

-- 删除表
DROP TABLE supertrampai_tbl ;

-- 插入数据
INSERT INTO supertrampai_tbl ( supertrampai_title, supertrampai_author,submission_date )
   VALUES
   ( "supertrampai_title1", "supertrampai_author1",NOW() );
   

-- 查询 OFFSET：指定偏移量，SELECT将从此处开始返回记录 
SELECT field1, field2,...fieldN 
FROM table_name1, table_name2...
[WHERE Clause]
[OFFSET M ][LIMIT N]

-- 查询
SELECT field1, field2,...fieldN table_name1, table_name2...
[WHERE condition1 [AND [OR]] condition2.....

-- 修改
UPDATE supertrampai_tbl SET field1 = NEW-value1, field2 = NEW-value2
[WHERE Clause]

-- 删除
DELETE FROM supertrampai_tbl [WHERE Clause]

-- ASC或DESC以升序或降序获得结果

-- IS NULL - 如果列值为NULL，则此运算符返回true。

-- IS NOT NULL - 如果列值不为NULL，则此运算符返回true。

-- <=> - 此运算符比较值（即使对于=运算符），即使对于两个NULL值也是如此。


-- ^	字符串的开头
-- $	字符串结束
-- 。	任何一个角色
-- [...]	方括号之间列出的任何字符
-- [^ ...]	方括号之间未列出的任何字符
-- P1 | P2 | P3	轮换; 匹配任何模式p1，p2或p3
-- *	前面元素的零个或多个实例
-- +	前一个元素的一个或多个实例
-- {N}	n前面元素的实例
-- {M，N}	m到前面元素的n个实例


-- 如果您的MySQL安装支持InnoDB表，只需在表创建语句中添加TYPE = InnoDB定义即可。

-- 改变表结构
-- 删除某一列i
ALTER TABLE supertrampai_tbl  DROP i;

-- 新建列
ALTER TABLE supertrampai_tbl ADD i INT;

-- 在c列新增列i
ALTER TABLE supertrampai_tbl ADD i INT AFTER c;

-- 将列c从CHAR（1）更改为CHAR（10）
 ALTER TABLE supertrampai_tbl MODIFY c CHAR(10);
 
 -- CHANGE将j从BIGINT转换回INT而不更改列名
 ALTER TABLE supertrampai_tbl CHANGE j j INT;
 
 -- 更改（更改）列的默认值
ALTER TABLE supertrampai_tbl ALTER i SET DEFAULT 1000;

-- 使用DROP子句和ALTER命令从任何列中删除默认约束
ALTER TABLE supertrampai_tbl ALTER i DROP DEFAULT;

-- 改变（改变）表格类型
ALTER TABLE supertrampai_tbl TYPE = MYISAM;
-- 查找表的当前类型，请使用SHOW TABLE STATUS语句。

-- 重命名（更改）表
ALTER TABLE supertrampai_tbl RENAME TO alter_tbl;

-- 创建索引
CREATE UNIQUE INDEX index_name ON table_name ( column1, column2,...);

-- ALTER命令添加和删除INDEX
ALTER TABLE tbl_name ADD PRIMARY KEY（column_list） -- 此语句添加PRIMARY KEY，这意味着索引值必须唯一且不能为NULL。

ALTER TABLE tbl_name ADD UNIQUE index_name（column_list） -- 此语句创建一个值必须唯一的索引（NULL值除外，它可能多次出现）。

ALTER TABLE tbl_name ADD INDEX index_name（column_list） -- 这会添加一个普通索引，其中任何值可能会出现多次。

ALTER TABLE tbl_name ADD FULLTEXT index_name（column_list） -- 这将创建一个特殊的FULLTEXT索引，用于文本搜索。

-- 使用DROP子句和ALTER命令删除任何INDEX
ALTER TABLE testalter_tbl DROP INDEX (c);

-- ALTER用于添加和删除PRIMARY KEY的命令
ALTER TABLE testalter_tbl MODIFY i INT NOT NULL;
ALTER TABLE testalter_tbl ADD PRIMARY KEY (i);

-- 创建临时表
 CREATE TEMPORARY TABLE SalesSummary (
product_name VARCHAR(50) NOT NULL
, total_sales DECIMAL(12,2) NOT NULL DEFAULT 0.00
 , avg_unit_price DECIMAL(7,2) NOT NULL DEFAULT 0.00
, total_units_sold INT UNSIGNED NOT NULL DEFAULT 0
);


-- clone表
-- 1. 获取有关表格的完整结构。
SHOW CREATE TABLE supertrampai_tbl;
-- 2. 执行上面show出来 sql语句
-- 3.insert数据

CREATE TABLE `clone_supertrampai_tbl` (
  `supertrampai_id` INT(11) NOT NULL AUTO_INCREMENT,
  `supertrampai_title` VARCHAR(100) NOT NULL,
  `supertrampai_author` VARCHAR(40) NOT NULL,
  `submission_date` DATE DEFAULT NULL,
  PRIMARY KEY (`supertrampai_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
-- 3.把旧表中的数据，insert到clone表中
INSERT INTO clone_supertrampai_tbl (supertrampai_id,
supertrampai_title,
supertrampai_author,
submission_date)
SELECT supertrampai_id,supertrampai_title,
supertrampai_author,submission_date
FROM supertrampai_tbl;

-- 查询clone数据库
SELECT * FROM clone_supertrampai_tbl;

-- 显示当前用户
SELECT USER()

-- 显示当前数据库名称
SELECT DATABASE();

-- 把自增id重置，从1开始
ALTER TABLE insect DROP id;

ALTER TABLE insect
ADD id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST, -- AUTO_INCREMENT = 100 在自增id中，指定开始值
ADD PRIMARY KEY (id);

-- 强制唯一性的另一种方法是向表中添加UNIQUE索引而不是PRIMARY KEY。
CREATE TABLE person_tbl (
   first_name CHAR(20) NOT NULL,
   last_name CHAR(20) NOT NULL,
   sex CHAR(10)
   UNIQUE (last_name, first_name)
);

-- 计算重复记录的查询。
SELECT COUNT(*) AS repetitions, last_name, first_name
FROM person_tbl
GROUP BY last_name, first_name
HAVING repetitions > 1;

-- 消除查询结果中的重复项  使用DISTINCT命令和SELECT语句来查找表中可用的唯一记录。

SELECT DISTINCT last_name, first_name
FROM person_tbl
ORDER BY last_name;

-- 使用表替换删除重复项
CREATE TABLE tmp SELECT last_name, first_name, sex
FROM person_tbl;
GROUP BY (last_name, first_name);

DROP TABLE person_tbl;
ALTER TABLE tmp RENAME TO person_tbl;