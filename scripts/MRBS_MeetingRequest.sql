-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: MRBS
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `MeetingRequest`
--

DROP TABLE IF EXISTS `MeetingRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MeetingRequest` (
  `requestId` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `requestedOn` datetime DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `meetingRoom_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`requestId`),
  KEY `FKlwy2oqvtblpmgbyhxvjv9p2rw` (`meetingRoom_id`),
  KEY `FKrsmv34n95fh9g5t48hjcxe2g4` (`resource_id`),
  KEY `FKge1t30vbueq2egheenky05w2u` (`user_username`),
  CONSTRAINT `FKge1t30vbueq2egheenky05w2u` FOREIGN KEY (`user_username`) REFERENCES `Login` (`username`),
  CONSTRAINT `FKlwy2oqvtblpmgbyhxvjv9p2rw` FOREIGN KEY (`meetingRoom_id`) REFERENCES `MeetingRoom` (`id`),
  CONSTRAINT `FKrsmv34n95fh9g5t48hjcxe2g4` FOREIGN KEY (`resource_id`) REFERENCES `Resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MeetingRequest`
--

LOCK TABLES `MeetingRequest` WRITE;
/*!40000 ALTER TABLE `MeetingRequest` DISABLE KEYS */;
INSERT INTO `MeetingRequest` VALUES ('H3354587','2019-09-16','11:00:00','2019-08-30 17:35:36','10:00:00','ACCEPT',1,1,'HR'),('H560547','2019-09-02','11:00:00','2019-08-30 17:35:36','10:00:00','ACCEPT',1,1,'HR'),('H6924571','2019-09-23','11:00:00','2019-08-30 17:35:36','10:00:00','ACCEPT',1,1,'HR'),('H8832559','2019-09-09','11:00:00','2019-08-30 17:35:36','10:00:00','ACCEPT',1,1,'HR'),('U3984808','2019-08-30','11:30:00','2019-08-30 17:35:21','10:20:00','ACCEPT',1,1,'user'),('U4994246','2019-09-03','11:30:00','2019-08-30 17:35:21','10:20:00','REJECT',1,1,'user'),('U5434594','2019-08-30','22:11:00','2019-08-30 17:34:58','11:21:00','CANCEL',1,1,'user'),('U5782452','2019-09-05','11:30:00','2019-08-30 17:35:21','10:20:00','REJECT',1,1,'user'),('U7286351','2019-09-02','11:30:00','2019-08-30 17:35:21','10:20:00','NEW',1,1,'user'),('U7324737','2019-09-04','11:30:00','2019-08-30 17:35:21','10:20:00','ACCEPT',1,1,'user');
/*!40000 ALTER TABLE `MeetingRequest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 17:36:23
