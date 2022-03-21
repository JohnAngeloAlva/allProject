-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: jobfinder
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblemployee`
--

DROP TABLE IF EXISTS `tblemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblemployee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(100) NOT NULL,
  `Age` int NOT NULL,
  `Email` varchar(50) NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Code` varchar(45) NOT NULL,
  `JobCategory` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblemployee`
--

LOCK TABLES `tblemployee` WRITE;
/*!40000 ALTER TABLE `tblemployee` DISABLE KEYS */;
INSERT INTO `tblemployee` VALUES (1,'John ANgelo  ALva',12,'john','0954623235','BXCT','UI/UX Designer'),(2,'Alva',15,'alva','0645656285','BKT','UI/UX Designer'),(3,'JOhn Angelo',18,'johnjonas','09566511653121','VHSKS','UI/UX Designer'),(4,'JOhn Angelo',18,'johnjonas','09566511653121','VHSKS','UI/UX Designer'),(5,'Angelo',18,'johnjonas','09566511653121','VHSKS','UI/UX Designer'),(6,'qweqwe',123,'johnjonasangelo@gmail.com','1212414','$(\"#codes\").val()','UI/UX Designer'),(7,'JOhn agelo Alva',16,'johnjonasangelo@gmail.com','1212414','kdslkuxkitp','UI/UX Designer'),(8,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','xg92u73pqy','UI/UX Designer'),(9,'jonas',12,'lucena','cough','546546544','UI/UX Designer'),(10,'JOhn agelo Alva',16,'johnjonasangelo@gmail.com','1212414','5yj6hjoau1r','UI/UX Designer'),(11,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','gs67z7n5i8l','UI/UX Designer'),(12,'qweqwe',123,'markjonasangelo@yahoo.com','1212414','j4l7ojre4sj','UI/UX Designer'),(13,'JOhn agelo Alva',123,'markjonasangelo@yahoo.com','1212414','2hg1v3f6xkm','UI/UX Designer'),(14,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','8cieag1fuko','UI/UX Designer'),(15,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','h1e6gubft8','UI designer'),(16,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','9s2ug3x1cji','UI designer'),(17,'JOhn agelo Alva',123,'markjonasangelo@yahoo.com','1212414','l79y72dehb','UI designer'),(18,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','k28rklo4yf','UI designer'),(19,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','w9mz8zaxer','iI designer'),(20,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','vit13lg2jds','IT'),(21,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','4fh9iha5wjc','analytics'),(22,'JOhn agelo Alva',123,'johnjonasangelo@gmail.com','1212414','fpedjrzdmcv','KOAK'),(23,'JOhn agelo Alva',123,'markjonasangelo@yahoo.com','1212414','dkpheeyydql','dgg'),(24,'qweqwe',123,'johnjonasangelo@gmail.com','1212414','eeem6t52jab','IT');
/*!40000 ALTER TABLE `tblemployee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 23:33:18
