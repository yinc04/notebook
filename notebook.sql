-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: notebook
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `type` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`type`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('日记','小天'),('日记','小雨'),('日记','小飞'),('日记','李渊'),('生活','小天'),('生活','小雨'),('生活','小飞'),('生活','李渊');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `eventId` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `content` text,
  `level` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`eventId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (2,'吃饭','2024-12-11 21:27:00','2024-12-11 21:27:00','222','重要','作废','美食','小天'),(3,'喝水','2024-12-11 21:31:14','2024-12-11 21:31:14','333','一般','作废','日记','小天'),(4,'吃饭','2024-12-11 21:32:18','2024-12-11 21:32:18','444','紧急','完成','日记','小天'),(5,'睡觉','2024-12-22 20:00:00','2024-12-18 20:00:00','5555','重要','未完成','生活','小天'),(6,'活动111','2024-12-13 20:00:00','2024-12-02 20:00:00','抢活动','紧急','完成','生活','李渊'),(7,'生胶','2024-12-02 20:00:00','2024-12-03 20:00:00','洒','重要','未完成','日记','李渊'),(10,'睡觉','2024-12-18 20:00:00','2024-12-05 20:00:00','无','紧急','完成','日记','小雨'),(11,'吃饭','2024-12-17 20:00:00','2024-12-11 20:00:00','无','一般','作废','日记','小雨'),(12,'看书和写字','2024-12-11 20:00:00','2024-11-27 20:00:00','不知道','一般','作废','日记','小雨'),(13,'考试','2024-12-01 20:00:00','2024-11-26 20:00:00','无','一般','完成','生活','小雨'),(14,'吃饭和游戏','2024-12-10 20:00:00','2024-12-09 20:00:00','无','重要','完成','生活','小雨'),(15,'活动','2024-12-17 20:00:00','2024-12-20 20:00:00','抢活动','重要','未完成','日记','小飞'),(17,'标题2','2024-12-17 20:00:00','2024-12-11 20:00:00','111','紧急','作废','生活','小飞');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `trueName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('小天','洛小天','123456',21,'12345674850',NULL),('小明',NULL,'123',22,NULL,NULL),('小月',NULL,'123456',0,NULL,NULL),('小雨','林小雨','123456',20,'12456987414',NULL),('小飞',NULL,'123456',0,NULL,NULL),('李渊','pyy','123456',21,NULL,NULL),('林',NULL,'123456',0,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-13 22:56:50
