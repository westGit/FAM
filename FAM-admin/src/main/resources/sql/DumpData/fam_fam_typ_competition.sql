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
-- Table structure for table `fam_typ_competition`
--


--
-- Dumping data for table `fam_typ_competition`
--

LOCK TABLES `fam_typ_competition` WRITE;
/*!40000 ALTER TABLE `fam_typ_competition` DISABLE KEYS */;
INSERT INTO `fam_typ_competition` VALUES (37,'PH','2012-02-25 19:21:56',NULL,1,'Promotion Honneur',1,21),(38,'CF','2012-02-25 19:21:56',NULL,0,'Coupe de France',1,19),(39,'DH','2012-02-25 19:21:56',NULL,1,'Division Honneur',1,21),(40,'A','2012-02-25 19:21:56',NULL,0,'Match amical',1,21),(41,'EXL','2012-02-25 19:21:56',NULL,1,'Excellence',1,20),(42,'1DIV','2012-02-25 19:21:56',NULL,1,'1ere Division',1,20);
/*!40000 ALTER TABLE `fam_typ_competition` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-29  3:44:45