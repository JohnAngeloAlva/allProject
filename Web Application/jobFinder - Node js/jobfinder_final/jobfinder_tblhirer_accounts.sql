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
-- Table structure for table `tblhirer_accounts`
--

DROP TABLE IF EXISTS `tblhirer_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblhirer_accounts` (
  `Account_ID` int NOT NULL AUTO_INCREMENT,
  `Fname` varchar(50) NOT NULL,
  `Lname` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `PhoneNum` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Account_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhirer_accounts`
--

LOCK TABLES `tblhirer_accounts` WRITE;
/*!40000 ALTER TABLE `tblhirer_accounts` DISABLE KEYS */;
INSERT INTO `tblhirer_accounts` VALUES (1,'jonas','john','johnjonasangelo@gmail.com','09236548562','Alva'),(2,'Alva','johnas','johnjonasangelo@gmail.com','09236548562','john'),(3,'sad','asd','asd','asd','asd'),(4,'sadasd','asdasd','asdasd','asdasdasd','asdasd'),(5,'asd','asd','asd','asd','asd'),(6,'sadasd','asdasd','asdad','asdasdadad','asdasd'),(7,'qwe','qwe','qwe','qwe','qwe'),(8,'','','','',''),(9,'hgjhjgj','ghjghjghj','ghjgjhghj','ghjghjghjg','ghjghjg'),(10,'john angelo ','alva','jonas@mgil.com','09256238563','jonas'),(11,'qwe','wqe','qweqw','eqwewqeqw','qweqweqwe'),(12,'qwe','qwe','qwe','wqe','qweqwe'),(13,'asdsaaaaaaaaa','asdadssssssssss','asdasdsdsdsdsdsdsdsdsdsd','asdasdddddd','asdadasd'),(14,'asdasd','asdasdasd','asdasdasd','66465654161','asdasdasdas'),(15,'Alva','john','john@gmail.com','09362162626','jonas');
/*!40000 ALTER TABLE `tblhirer_accounts` ENABLE KEYS */;
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
