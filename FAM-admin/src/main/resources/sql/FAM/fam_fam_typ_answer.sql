CREATE DATABASE  IF NOT EXISTS `fam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fam`;
-- MySQL dump 10.13  Distrib 5.5.25, for Linux (x86_64)
--
-- Host: localhost    Database: fam
-- ------------------------------------------------------
-- Server version	5.5.25-log

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
-- Dumping data for table `fam_typ_answer`
--

LOCK TABLES `fam_typ_answer` WRITE;
/*!40000 ALTER TABLE `fam_typ_answer` DISABLE KEYS */;
INSERT INTO `fam_typ_answer` VALUES (1,'1','2012-07-30 20:54:50',NULL,'P','Présent',1),(2,'2','2012-07-30 20:54:50',NULL,'A','Absent',1),(3,'3','2012-07-30 20:54:50',NULL,'N','Ne sait pas',1),(4,'4','2012-07-30 20:54:50',NULL,'A','Absent : déplacement',1),(5,'5','2012-07-30 20:54:50',NULL,'A','Absent : week-end',1),(6,'6','2012-07-30 20:54:50',NULL,'A','Absent : blessure',1),(7,'7','2012-07-30 20:54:50',NULL,'P','Présent : diminué',1),(8,'8','2012-07-30 20:54:50',NULL,'P','Présent : en retard',1),(9,'9','2012-07-30 20:54:50',NULL,'P','Présent : une mi-temps',1),(11,'11','2012-07-30 20:54:50',NULL,'A','Absent : famille',1),(12,'12','2012-07-30 20:54:50',NULL,'A','Absent : congés',1),(13,'13','2012-07-30 20:54:50',NULL,'A','Absent : boulot',1);
/*!40000 ALTER TABLE `fam_typ_answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-31  3:45:33
