/*
SQLyog v10.2 
MySQL - 5.5.28 : Database - fantasticfunicular
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fantasticfunicular` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fantasticfunicular`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bname` varchar(100) NOT NULL COMMENT '图书名',
  `uid` varchar(100) NOT NULL COMMENT '图书作者',
  `publish` varchar(100) NOT NULL COMMENT '出版社',
  `publishdate` date NOT NULL COMMENT '出版日期',
  `page` int(11) DEFAULT NULL COMMENT '页数',
  `price` int(11) NOT NULL COMMENT '价格',
  `content` varchar(500) NOT NULL COMMENT '内容摘要',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='图书表';

/*Data for the table `book` */

insert  into `book`(`bid`,`bname`,`uid`,`publish`,`publishdate`,`page`,`price`,`content`) values (1,'name1','1','publishname1','2019-05-13',200,23,'testtest'),(2,'name2','2','publishname1','2019-05-13',200,23,'testtest'),(3,'name3','3','publishname1','2019-05-13',200,23,'testtest'),(4,'name4','4','publishname1','2019-05-13',200,23,'testtest'),(5,'name5','5','publishname1','2019-05-13',200,23,'testtest'),(6,'name6','6','publishname1','2019-05-13',200,23,'testtest'),(7,'name7','7','publishname1','2019-05-13',200,23,'testtest'),(8,'name8','1','publishname1','2019-05-13',200,23,'testtest'),(9,'name9','2','publishname1','2019-05-13',200,23,'testtest'),(10,'name10','3','publishname1','2019-05-13',200,23,'testtest'),(11,'name11','4','publishname1','2019-05-13',200,23,'testtest'),(12,'name12','5','publishname1','2019-05-13',200,23,'testtest');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1),(1),(1);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) values (1,'\0','用户管理',0,'0/','userInfo:view','menu','userInfo/userList'),(2,'\0','用户添加',1,'0/1','userInfo:add','button','userInfo/userAdd'),(3,'\0','用户删除',1,'0/1','userInfo:del','button','userInfo/userDel');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`available`,`description`,`role`) values (1,'\0','管理员','admin'),(2,'\0','VIP会员','vip'),(3,'','test','test');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`permission_id`,`role_id`) values (1,1),(1,1),(2,1),(3,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `uid` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`),
  CONSTRAINT `FKgkmyslkrfeyn9ukmolvek8b8f` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`uid`,`role_id`) values (1,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `createdt` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updatedt` datetime DEFAULT NULL,
  `usertype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `uid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`uid`,`name`,`password`,`salt`,`state`,`username`) values (1,'管理员','123456','8d78869f470951332959580424d4bf4f',0,'admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
