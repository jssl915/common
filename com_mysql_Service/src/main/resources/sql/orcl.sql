-- MySQL dump 10.13  Distrib 6.0.11-alpha, for Win64 (unknown)
--
-- Host: localhost    Database: orcl
-- ------------------------------------------------------
-- Server version	6.0.11-alpha-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_s_detail`
--

DROP TABLE IF EXISTS `t_s_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_detail` (
  `detail_id` int(16) NOT NULL AUTO_INCREMENT,
  `dict_id` int(16) DEFAULT NULL,
  `detail_name` varchar(32) DEFAULT NULL,
  `detail_value` int(16) DEFAULT NULL,
  `detail_desc` varchar(256) DEFAULT NULL,
  `detail_status` int(16) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  `update_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_detail`
--

LOCK TABLES `t_s_detail` WRITE;
/*!40000 ALTER TABLE `t_s_detail` DISABLE KEYS */;
INSERT INTO `t_s_detail` VALUES (8,1,'启用',1,'启用状态',1,'2015-01-04 14:04:28','2015-01-06 11:02:33'),(9,1,'禁止',2,'禁止状态',1,'2015-01-04 14:04:39','2015-01-04 14:04:39');
/*!40000 ALTER TABLE `t_s_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_dict`
--

DROP TABLE IF EXISTS `t_s_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_dict` (
  `dict_id` int(16) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(32) DEFAULT NULL,
  `dict_desc` varchar(256) DEFAULT NULL,
  `dict_status` int(8) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  `update_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_dict`
--

LOCK TABLES `t_s_dict` WRITE;
/*!40000 ALTER TABLE `t_s_dict` DISABLE KEYS */;
INSERT INTO `t_s_dict` VALUES (1,'状态','1启用2禁止',1,'2015-01-04 11:07:56','2015-01-13 14:07:08');
/*!40000 ALTER TABLE `t_s_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_log`
--

DROP TABLE IF EXISTS `t_s_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_log` (
  `log_id` int(16) NOT NULL AUTO_INCREMENT,
  `action_url` varchar(128) DEFAULT NULL,
  `log_time` varchar(19) DEFAULT NULL,
  `user_ip` varchar(15) DEFAULT NULL,
  `user_id` int(16) DEFAULT NULL,
  `log_desc` varchar(128) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_log`
--

LOCK TABLES `t_s_log` WRITE;
/*!40000 ALTER TABLE `t_s_log` DISABLE KEYS */;
INSERT INTO `t_s_log` VALUES (1,'/com_mysql_System/index/tree','2015-01-22 11:19:03','0:0:0:0:0:0:0:1',42,NULL,'lj'),(2,'/system/prg/log/init','2015-01-22 11:19:04','0:0:0:0:0:0:0:1',42,NULL,'lj'),(3,'/system/prg/log/init','2015-01-22 11:19:06','0:0:0:0:0:0:0:1',42,NULL,'lj'),(4,'/system/prg/user/init','2015-01-22 11:19:08','0:0:0:0:0:0:0:1',42,NULL,'lj'),(5,'/system/prg/menu/init','2015-01-22 11:19:09','0:0:0:0:0:0:0:1',42,NULL,'lj'),(6,'/system/prg/menu/tree','2015-01-22 11:19:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(7,'/system/prg/role/init','2015-01-22 11:19:11','0:0:0:0:0:0:0:1',42,NULL,'lj'),(8,'/system/prg/dict/init','2015-01-22 11:19:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(9,'/system/prg/log/init','2015-01-22 11:19:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(10,'/com_mysql_System/cy/img/upload','2015-01-22 11:19:16','0:0:0:0:0:0:0:1',42,NULL,'lj'),(11,'/system/prg/user/init','2015-01-22 11:19:18','0:0:0:0:0:0:0:1',42,NULL,'lj'),(12,'/system/prg/menu/init','2015-01-22 11:19:21','0:0:0:0:0:0:0:1',42,NULL,'lj'),(13,'/system/prg/menu/tree','2015-01-22 11:19:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(14,'/system/prg/user/init','2015-01-22 11:19:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(15,'/system/prg/user/init','2015-01-22 11:19:39','0:0:0:0:0:0:0:1',42,NULL,'lj'),(16,'/system/prg/menu/init','2015-01-22 11:19:54','0:0:0:0:0:0:0:1',42,NULL,'lj'),(17,'/system/prg/menu/tree','2015-01-22 11:19:54','0:0:0:0:0:0:0:1',42,NULL,'lj'),(18,'/system/prg/role/init','2015-01-22 11:19:55','0:0:0:0:0:0:0:1',42,NULL,'lj'),(19,'/system/prg/user/init','2015-01-22 11:19:56','0:0:0:0:0:0:0:1',42,NULL,'lj'),(20,'/system/prg/menu/init','2015-01-22 11:20:06','0:0:0:0:0:0:0:1',42,NULL,'lj'),(21,'/system/prg/menu/tree','2015-01-22 11:20:06','0:0:0:0:0:0:0:1',42,NULL,'lj'),(22,'/com_mysql_System/index/tree','2015-01-22 11:20:39','0:0:0:0:0:0:0:1',42,NULL,'lj'),(23,'/system/prg/menu/init','2015-01-22 11:20:40','0:0:0:0:0:0:0:1',42,NULL,'lj'),(24,'/system/prg/menu/tree','2015-01-22 11:20:40','0:0:0:0:0:0:0:1',42,NULL,'lj'),(25,'/com_mysql_System/index/tree','2015-01-22 11:20:52','0:0:0:0:0:0:0:1',42,NULL,'lj'),(26,'/system/prg/menu/init','2015-01-22 11:20:53','0:0:0:0:0:0:0:1',42,NULL,'lj'),(27,'/system/prg/menu/tree','2015-01-22 11:20:54','0:0:0:0:0:0:0:1',42,NULL,'lj'),(28,'/system/prg/role/init','2015-01-22 11:21:00','0:0:0:0:0:0:0:1',42,NULL,'lj'),(29,'/system/prg/menu/init','2015-01-22 11:21:01','0:0:0:0:0:0:0:1',42,NULL,'lj'),(30,'/system/prg/menu/tree','2015-01-22 11:21:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(31,'/system/prg/user/init','2015-01-22 11:21:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(32,'/system/prg/role/init','2015-01-22 11:21:04','0:0:0:0:0:0:0:1',42,NULL,'lj'),(33,'/com_mysql_System/system/prg/role/menuTree','2015-01-22 11:21:06','0:0:0:0:0:0:0:1',42,NULL,'lj'),(34,'/com_mysql_System/system/prg/role/menuTree','2015-01-22 11:21:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(35,'/system/prg/menu/init','2015-01-22 11:21:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(36,'/system/prg/menu/tree','2015-01-22 11:21:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(37,'/system/prg/user/init','2015-01-22 11:21:13','0:0:0:0:0:0:0:1',42,NULL,'lj'),(38,'/system/prg/menu/init','2015-01-22 11:21:14','0:0:0:0:0:0:0:1',42,NULL,'lj'),(39,'/system/prg/menu/tree','2015-01-22 11:21:14','0:0:0:0:0:0:0:1',42,NULL,'lj'),(40,'/com_mysql_System/system/login','2015-01-22 11:27:56','0:0:0:0:0:0:0:1',42,NULL,'lj'),(41,'/com_mysql_System/index/tree','2015-01-22 11:27:57','0:0:0:0:0:0:0:1',42,NULL,'lj'),(42,'/system/prg/user/init','2015-01-22 11:27:58','0:0:0:0:0:0:0:1',42,NULL,'lj'),(43,'/system/prg/menu/init','2015-01-22 11:27:59','0:0:0:0:0:0:0:1',42,NULL,'lj'),(44,'/system/prg/menu/tree','2015-01-22 11:28:00','0:0:0:0:0:0:0:1',42,NULL,'lj'),(45,'/system/prg/user/init','2015-01-22 11:28:00','0:0:0:0:0:0:0:1',42,NULL,'lj'),(46,'/com_mysql_System/index/tree','2015-01-22 11:31:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(47,'/system/prg/user/init','2015-01-22 11:31:21','0:0:0:0:0:0:0:1',42,NULL,'lj'),(48,'/system/prg/menu/init','2015-01-22 11:31:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(49,'/system/prg/menu/tree','2015-01-22 11:31:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(50,'/system/prg/user/init','2015-01-22 11:31:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(51,'/com_mysql_System/index/tree','2015-01-22 11:31:33','0:0:0:0:0:0:0:1',42,NULL,'lj'),(52,'/system/prg/user/init','2015-01-22 11:31:34','0:0:0:0:0:0:0:1',42,NULL,'lj'),(53,'/system/prg/menu/init','2015-01-22 11:31:35','0:0:0:0:0:0:0:1',42,NULL,'lj'),(54,'/system/prg/menu/tree','2015-01-22 11:31:35','0:0:0:0:0:0:0:1',42,NULL,'lj'),(55,'/system/prg/log/init','2015-01-22 11:31:38','0:0:0:0:0:0:0:1',42,NULL,'lj'),(56,'/system/prg/log/init','2015-01-22 11:31:52','0:0:0:0:0:0:0:1',42,NULL,'lj'),(57,'/system/prg/log/init','2015-01-22 12:05:43','0:0:0:0:0:0:0:1',42,NULL,'lj'),(58,'/system/prg/log/init','2015-01-22 12:06:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(59,'/system/prg/log/init','2015-01-22 12:10:53','0:0:0:0:0:0:0:1',42,NULL,'lj'),(60,'/system/prg/log/init','2015-01-22 12:11:46','0:0:0:0:0:0:0:1',42,NULL,'lj'),(61,'/system/prg/log/init','2015-01-22 12:12:44','0:0:0:0:0:0:0:1',42,NULL,'lj'),(62,'/com_mysql_System/index/tree','2015-01-22 12:13:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(63,'/system/prg/log/init','2015-01-22 12:13:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(64,'/system/prg/user/init','2015-01-22 12:13:19','0:0:0:0:0:0:0:1',42,NULL,'lj'),(65,'/system/prg/menu/init','2015-01-22 12:13:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(66,'/system/prg/menu/tree','2015-01-22 12:13:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(67,'/system/prg/role/init','2015-01-22 12:13:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(68,'/system/prg/dict/init','2015-01-22 12:13:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(69,'/system/prg/log/init','2015-01-22 12:13:24','0:0:0:0:0:0:0:1',42,NULL,'lj'),(70,'/system/prg/user/init','2015-01-22 12:13:25','0:0:0:0:0:0:0:1',42,NULL,'lj'),(71,'/system/prg/menu/init','2015-01-22 12:13:48','0:0:0:0:0:0:0:1',42,NULL,'lj'),(72,'/system/prg/menu/tree','2015-01-22 12:13:49','0:0:0:0:0:0:0:1',42,NULL,'lj'),(73,'/system/prg/user/init','2015-01-22 12:13:49','0:0:0:0:0:0:0:1',42,NULL,'lj'),(74,'/system/prg/menu/init','2015-01-22 12:13:59','0:0:0:0:0:0:0:1',42,NULL,'lj'),(75,'/system/prg/menu/tree','2015-01-22 12:14:00','0:0:0:0:0:0:0:1',42,NULL,'lj'),(76,'/com_mysql_System/index/tree','2015-01-22 12:16:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(77,'/system/prg/menu/init','2015-01-22 12:16:04','0:0:0:0:0:0:0:1',42,NULL,'lj'),(78,'/system/prg/menu/tree','2015-01-22 12:16:04','0:0:0:0:0:0:0:1',42,NULL,'lj'),(79,'/com_mysql_System/index/tree','2015-01-22 12:18:01','0:0:0:0:0:0:0:1',42,NULL,'lj'),(80,'/system/prg/menu/init','2015-01-22 12:18:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(81,'/system/prg/menu/tree','2015-01-22 12:18:03','0:0:0:0:0:0:0:1',42,NULL,'lj'),(82,'/com_mysql_System/index/tree','2015-01-22 12:18:13','0:0:0:0:0:0:0:1',42,NULL,'lj'),(83,'/system/prg/menu/init','2015-01-22 12:18:14','0:0:0:0:0:0:0:1',42,NULL,'lj'),(84,'/system/prg/menu/tree','2015-01-22 12:18:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(85,'/com_mysql_System/index/tree','2015-01-22 12:20:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(86,'/system/prg/menu/init','2015-01-22 12:20:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(87,'/system/prg/menu/tree','2015-01-22 12:20:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(88,'/com_mysql_System/index/tree','2015-01-22 12:20:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(89,'/system/prg/menu/init','2015-01-22 12:20:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(90,'/system/prg/menu/tree','2015-01-22 12:20:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(91,'/com_mysql_System/index/tree','2015-01-22 12:20:51','0:0:0:0:0:0:0:1',42,NULL,'lj'),(92,'/system/prg/menu/init','2015-01-22 12:20:52','0:0:0:0:0:0:0:1',42,NULL,'lj'),(93,'/system/prg/menu/tree','2015-01-22 12:20:53','0:0:0:0:0:0:0:1',42,NULL,'lj'),(94,'/system/prg/menu/init','2015-01-22 12:21:01','0:0:0:0:0:0:0:1',42,NULL,'lj'),(95,'/system/prg/menu/tree','2015-01-22 12:21:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(96,'/com_mysql_System/system/login','2015-01-22 12:40:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(97,'/com_mysql_System/index/tree','2015-01-22 12:40:08','0:0:0:0:0:0:0:1',42,NULL,'lj'),(98,'/system/prg/user/init','2015-01-22 12:40:09','0:0:0:0:0:0:0:1',42,NULL,'lj'),(99,'/system/prg/menu/init','2015-01-22 12:40:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(100,'/system/prg/menu/tree','2015-01-22 12:40:11','0:0:0:0:0:0:0:1',42,NULL,'lj'),(101,'/system/prg/role/init','2015-01-22 12:40:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(102,'/system/prg/user/init','2015-01-22 12:40:13','0:0:0:0:0:0:0:1',42,NULL,'lj'),(103,'/system/prg/log/init','2015-01-22 12:40:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(104,'/system/prg/user/init','2015-01-22 12:40:16','0:0:0:0:0:0:0:1',42,NULL,'lj'),(105,'/system/prg/user/showAdd','2015-01-22 12:40:18','0:0:0:0:0:0:0:1',42,NULL,'lj'),(106,'/system/prg/menu/init','2015-01-22 12:40:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(107,'/system/prg/menu/tree','2015-01-22 12:40:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(108,'/system/prg/user/init','2015-01-22 12:40:31','0:0:0:0:0:0:0:1',42,NULL,'lj'),(109,'/system/prg/user/init','2015-01-22 12:49:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(110,'/system/prg/menu/init','2015-01-22 12:49:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(111,'/system/prg/menu/tree','2015-01-22 12:49:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(112,'/system/prg/user/init','2015-01-22 12:49:18','0:0:0:0:0:0:0:1',42,NULL,'lj'),(113,'/system/prg/menu/init','2015-01-22 12:49:19','0:0:0:0:0:0:0:1',42,NULL,'lj'),(114,'/system/prg/menu/tree','2015-01-22 12:49:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(115,'/system/prg/user/init','2015-01-22 12:49:24','0:0:0:0:0:0:0:1',42,NULL,'lj'),(116,'/system/prg/user/showAdd','2015-01-22 12:49:26','0:0:0:0:0:0:0:1',42,NULL,'lj'),(117,'/system/prg/user/checkUserName','2015-01-22 12:49:28','0:0:0:0:0:0:0:1',42,NULL,'lj'),(118,'/system/prg/user/insert','2015-01-22 12:49:30','0:0:0:0:0:0:0:1',42,NULL,'lj'),(119,'/system/prg/user/delete','2015-01-22 12:49:37','0:0:0:0:0:0:0:1',42,NULL,'lj'),(120,'/system/prg/user/init','2015-01-22 12:49:38','0:0:0:0:0:0:0:1',42,NULL,'lj'),(121,'/system/prg/menu/init','2015-01-22 12:49:39','0:0:0:0:0:0:0:1',42,NULL,'lj'),(122,'/system/prg/menu/tree','2015-01-22 12:49:40','0:0:0:0:0:0:0:1',42,NULL,'lj'),(123,'/system/prg/user/init','2015-01-22 12:49:40','0:0:0:0:0:0:0:1',42,NULL,'lj'),(124,'/system/prg/role/init','2015-01-22 12:49:41','0:0:0:0:0:0:0:1',42,NULL,'lj'),(125,'/system/prg/user/init','2015-01-22 12:49:42','0:0:0:0:0:0:0:1',42,NULL,'lj'),(126,'/system/prg/log/init','2015-01-22 12:49:43','0:0:0:0:0:0:0:1',42,NULL,'lj'),(127,'/system/prg/user/init','2015-01-22 12:49:45','0:0:0:0:0:0:0:1',42,NULL,'lj'),(128,'/system/prg/user/init','2015-01-22 12:50:05','0:0:0:0:0:0:0:1',42,NULL,'lj'),(129,'/system/prg/menu/init','2015-01-22 12:50:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(130,'/system/prg/menu/tree','2015-01-22 12:50:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(131,'/system/prg/user/init','2015-01-22 12:50:08','0:0:0:0:0:0:0:1',42,NULL,'lj'),(132,'/system/prg/log/init','2015-01-22 13:12:53','0:0:0:0:0:0:0:1',42,NULL,'lj'),(133,'/system/prg/menu/init','2015-01-22 13:13:02','0:0:0:0:0:0:0:1',42,NULL,'lj'),(134,'/system/prg/menu/tree','2015-01-22 13:13:03','0:0:0:0:0:0:0:1',42,NULL,'lj'),(135,'/system/prg/user/init','2015-01-22 13:13:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(136,'/system/prg/menu/init','2015-01-22 13:13:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(137,'/system/prg/menu/tree','2015-01-22 13:13:10','0:0:0:0:0:0:0:1',42,NULL,'lj'),(138,'/system/prg/role/init','2015-01-22 13:13:11','0:0:0:0:0:0:0:1',42,NULL,'lj'),(139,'/system/prg/dict/init','2015-01-22 13:13:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(140,'/system/prg/log/init','2015-01-22 13:13:13','0:0:0:0:0:0:0:1',42,NULL,'lj'),(141,'/system/prg/dict/init','2015-01-22 13:13:14','0:0:0:0:0:0:0:1',42,NULL,'lj'),(142,'/system/prg/role/init','2015-01-22 13:13:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(143,'/com_mysql_System/system/prg/role/menuTree','2015-01-22 13:13:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(144,'/com_mysql_System/system/prg/role/bindMenu','2015-01-22 13:13:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(145,'/com_mysql_System/index/tree','2015-01-22 13:13:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(146,'/com_mysql_System/system/prg/role/menuTree','2015-01-22 13:13:23','0:0:0:0:0:0:0:1',42,NULL,'lj'),(147,'/com_mysql_System/system/prg/role/bindMenu','2015-01-22 13:13:26','0:0:0:0:0:0:0:1',42,NULL,'lj'),(148,'/com_mysql_System/index/tree','2015-01-22 13:13:26','0:0:0:0:0:0:0:1',42,NULL,'lj'),(149,'/com_mysql_System/system/prg/role/userDialog','2015-01-22 13:13:32','0:0:0:0:0:0:0:1',42,NULL,'lj'),(150,'/system/prg/user/init','2015-01-22 13:13:35','0:0:0:0:0:0:0:1',42,NULL,'lj'),(151,'/system/prg/menu/init','2015-01-22 13:13:36','0:0:0:0:0:0:0:1',42,NULL,'lj'),(152,'/system/prg/menu/tree','2015-01-22 13:13:36','0:0:0:0:0:0:0:1',42,NULL,'lj'),(153,'/system/prg/user/init','2015-01-22 13:13:42','0:0:0:0:0:0:0:1',42,NULL,'lj'),(154,'/system/prg/menu/init','2015-01-22 13:13:43','0:0:0:0:0:0:0:1',42,NULL,'lj'),(155,'/system/prg/menu/tree','2015-01-22 13:13:44','0:0:0:0:0:0:0:1',42,NULL,'lj'),(156,'/system/prg/dict/init','2015-01-22 13:13:47','0:0:0:0:0:0:0:1',42,NULL,'lj'),(157,'/system/prg/log/init','2015-01-22 13:13:48','0:0:0:0:0:0:0:1',42,NULL,'lj'),(158,'/system/prg/user/init','2015-01-22 13:13:49','0:0:0:0:0:0:0:1',42,NULL,'lj'),(159,'/system/prg/user/init','2015-01-22 13:13:57','0:0:0:0:0:0:0:1',42,NULL,'lj'),(160,'/com_mysql_System/system/login','2015-01-22 13:14:11','0:0:0:0:0:0:0:1',42,NULL,'lj'),(161,'/com_mysql_System/index/tree','2015-01-22 13:14:11','0:0:0:0:0:0:0:1',42,NULL,'lj'),(162,'/system/prg/user/init','2015-01-22 13:14:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(163,'/system/prg/user/init','2015-01-22 13:33:07','0:0:0:0:0:0:0:1',42,NULL,'lj'),(164,'/system/prg/menu/init','2015-01-22 13:33:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(165,'/system/prg/menu/tree','2015-01-22 13:33:12','0:0:0:0:0:0:0:1',42,NULL,'lj'),(166,'/system/prg/dict/init','2015-01-22 13:33:13','0:0:0:0:0:0:0:1',42,NULL,'lj'),(167,'/system/prg/role/init','2015-01-22 13:33:14','0:0:0:0:0:0:0:1',42,NULL,'lj'),(168,'/system/prg/log/init','2015-01-22 13:33:15','0:0:0:0:0:0:0:1',42,NULL,'lj'),(169,'/system/prg/user/init','2015-01-22 13:33:16','0:0:0:0:0:0:0:1',42,NULL,'lj'),(170,'/com_mysql_System/cy/img/upload','2015-01-22 13:33:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(171,'/com_mysql_System/cy/img/photowall','2015-01-22 13:33:17','0:0:0:0:0:0:0:1',42,NULL,'lj'),(172,'/system/prg/user/init','2015-01-22 13:33:18','0:0:0:0:0:0:0:1',42,NULL,'lj'),(173,'/system/prg/role/init','2015-01-22 13:33:19','0:0:0:0:0:0:0:1',42,NULL,'lj'),(174,'/system/prg/dict/init','2015-01-22 13:33:20','0:0:0:0:0:0:0:1',42,NULL,'lj'),(175,'/system/prg/dict/showAdd','2015-01-22 13:33:22','0:0:0:0:0:0:0:1',42,NULL,'lj'),(176,'/system/prg/role/init','2015-01-22 13:33:24','0:0:0:0:0:0:0:1',42,NULL,'lj'),(177,'/system/prg/menu/init','2015-01-22 13:33:25','0:0:0:0:0:0:0:1',42,NULL,'lj'),(178,'/system/prg/menu/tree','2015-01-22 13:33:25','0:0:0:0:0:0:0:1',42,NULL,'lj'),(179,'/system/prg/user/init','2015-01-22 13:33:26','0:0:0:0:0:0:0:1',42,NULL,'lj'),(180,'/system/prg/menu/init','2015-01-22 13:33:27','0:0:0:0:0:0:0:1',42,NULL,'lj'),(181,'/system/prg/menu/tree','2015-01-22 13:33:27','0:0:0:0:0:0:0:1',42,NULL,'lj'),(182,'/system/prg/user/init','2015-01-22 13:33:28','0:0:0:0:0:0:0:1',42,NULL,'lj'),(183,'/system/prg/user/init','2015-01-22 13:33:37','0:0:0:0:0:0:0:1',42,NULL,'lj'),(184,'/com_mysql_System/system/login','2015-01-22 13:33:43','0:0:0:0:0:0:0:1',42,NULL,'lj'),(185,'/com_mysql_System/index/tree','2015-01-22 13:33:43','0:0:0:0:0:0:0:1',42,NULL,'lj'),(186,'/system/prg/user/init','2015-01-22 13:33:49','0:0:0:0:0:0:0:1',42,NULL,'lj');
/*!40000 ALTER TABLE `t_s_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_menu`
--

DROP TABLE IF EXISTS `t_s_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_menu` (
  `menu_id` int(16) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) DEFAULT NULL,
  `menu_desc` varchar(256) DEFAULT NULL,
  `menu_url` varchar(128) DEFAULT NULL,
  `menu_pid` int(8) DEFAULT NULL,
  `menu_type` int(8) DEFAULT NULL,
  `menu_status` int(8) DEFAULT NULL,
  `menu_level` int(8) DEFAULT NULL,
  `menu_icon` varchar(128) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  `update_time` varchar(19) DEFAULT NULL,
  `menu_order` int(8) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_menu`
--

LOCK TABLES `t_s_menu` WRITE;
/*!40000 ALTER TABLE `t_s_menu` DISABLE KEYS */;
INSERT INTO `t_s_menu` VALUES (0,'资源树','资源树',NULL,-1,1,1,0,NULL,'2014-12-30 12:38:14','2014-12-30 12:38:14',1),(1,'系统管理','系统管理',NULL,0,1,1,1,NULL,NULL,'2014-12-30 13:07:08',1),(3,'系统管理员管理','用户管理','/system/prg/user/init',1,1,1,2,NULL,NULL,'2014-12-30 13:36:00',1),(4,'系统角色管理','角色管理','/system/prg/role/init',1,1,1,2,NULL,NULL,'2014-12-30 13:36:42',3),(6,'系统菜单管理','菜单管理','/system/prg/menu/init',1,1,1,2,NULL,NULL,'2015-01-04 10:11:16',2),(60,'系统日志管理',NULL,'/system/prg/log/init',1,NULL,1,2,NULL,'2014-12-30 13:37:07','2015-01-04 10:11:37',5),(90,'系统参数管理',NULL,'/system/prg/dict/init',1,NULL,1,2,NULL,'2015-01-04 10:11:48','2015-01-06 11:10:46',4),(130,'图片组件',NULL,NULL,0,NULL,1,1,NULL,'2015-01-08 09:43:05','2015-01-14 09:03:46',2),(131,'图片上传功能',NULL,'cy/img/upload',130,NULL,1,2,NULL,'2015-01-08 09:44:16','2015-01-14 09:06:21',1),(132,'图表组件',NULL,NULL,0,NULL,1,1,NULL,'2015-01-08 09:44:47','2015-01-14 15:11:31',3),(170,'图片拖拽示例',NULL,'cy/img/photowall',130,NULL,1,2,NULL,'2015-01-14 09:05:58','2015-01-14 09:18:42',1),(190,'基本折线图',NULL,'cy/chart/baseline',132,NULL,1,2,NULL,'2015-01-14 15:26:28','2015-01-14 15:26:47',1),(191,'基本饼图',NULL,'cy/chart/basepie',132,NULL,1,2,NULL,'2015-01-14 15:29:03','2015-01-14 15:29:25',2),(192,'基本柱状图',NULL,'cy/chart/basecolumn',132,NULL,1,2,NULL,'2015-01-14 15:33:34','2015-01-14 15:33:34',3);
/*!40000 ALTER TABLE `t_s_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_role`
--

DROP TABLE IF EXISTS `t_s_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_role` (
  `role_id` int(16) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL,
  `role_desc` varchar(256) DEFAULT NULL,
  `role_order` int(8) DEFAULT NULL,
  `role_type` int(8) DEFAULT NULL,
  `role_status` int(8) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  `update_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_role`
--

LOCK TABLES `t_s_role` WRITE;
/*!40000 ALTER TABLE `t_s_role` DISABLE KEYS */;
INSERT INTO `t_s_role` VALUES (1,'超级管理员','最高权限管理员',1,NULL,1,'2014-12-30 13:22:52','2014-12-30 13:22:52'),(3,'用户管理员','用户管理员',2,NULL,NULL,'2014-12-30 13:31:30','2014-12-30 13:31:30');
/*!40000 ALTER TABLE `t_s_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_role_menu`
--

DROP TABLE IF EXISTS `t_s_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_role_menu` (
  `role_id` int(16) DEFAULT NULL,
  `menu_id` int(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_role_menu`
--

LOCK TABLES `t_s_role_menu` WRITE;
/*!40000 ALTER TABLE `t_s_role_menu` DISABLE KEYS */;
INSERT INTO `t_s_role_menu` VALUES (3,3),(3,90),(3,4),(1,0),(1,1),(1,3),(1,6),(1,4),(1,90),(1,60),(1,130),(1,131),(1,170),(1,132),(1,190),(1,191),(1,192);
/*!40000 ALTER TABLE `t_s_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_user`
--

DROP TABLE IF EXISTS `t_s_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_user` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `user_order` int(8) DEFAULT NULL,
  `user_type` int(8) DEFAULT NULL,
  `user_status` int(8) DEFAULT NULL,
  `user_pwd` varchar(256) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  `update_time` varchar(19) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=424 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_user`
--

LOCK TABLES `t_s_user` WRITE;
/*!40000 ALTER TABLE `t_s_user` DISABLE KEYS */;
INSERT INTO `t_s_user` VALUES (42,'lj',1,1,1,'FBADE9E36A3F36D3D676C1B808451DD7','2014-12-29 10:45:46','2015-01-04 17:14:12','李晶'),(121,'abc',2,1,1,'900150983CD24FB0D6963F7D28E17F72','2015-01-06 13:07:05','2015-01-13 16:10:03','abc'),(161,'ff',1,1,1,'21218CCA77804D2BA1922C33E0151105','2015-01-12 15:25:30','2015-01-13 15:39:59','ff');
/*!40000 ALTER TABLE `t_s_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_s_user_role`
--

DROP TABLE IF EXISTS `t_s_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_s_user_role` (
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_s_user_role`
--

LOCK TABLES `t_s_user_role` WRITE;
/*!40000 ALTER TABLE `t_s_user_role` DISABLE KEYS */;
INSERT INTO `t_s_user_role` VALUES (42,1),(121,3),(121,1);
/*!40000 ALTER TABLE `t_s_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-22  6:33:43
