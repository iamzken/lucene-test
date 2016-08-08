/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.24 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `test`;

/*Table structure for table `photo` */

DROP TABLE IF EXISTS `photo`;

CREATE TABLE `photo` (
  `photo_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(11) DEFAULT NULL,
  `descr` text,
  `user_name` varchar(11) DEFAULT NULL,
  `tag_name` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`photo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=REDUNDANT;

/*Data for the table `photo` */

insert  into `photo`(`photo_id`,`title`,`descr`,`user_name`,`tag_name`) values (1,'美女1','美女','好人5','美女'),(2,'美女2','柯南，现在生产上ngix已经配置。但他们认为这是程序原因，临时配置。需要我们在应用上改掉，再恢复原有设置。\r\n你和陶小明再一起分析分析。谢谢。\r\n','美女','美女'),(3,'美女3','现在我们的修改无非是把nginx对应的cookie名字修改了一下而已，由默认的route该为了非route，\r\n这对现有nginx集群架构可以说无任何影响，如果非要改代码的话，那估计要费很大劲的………\r\n我觉得运维组的人员就是在瞎逼逼\r\n','',NULL),(4,'美女4','张柯楠 | Jack Adam         \r\n助理系统架构师 \r\n卓越管理中心\r\n',' ',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
