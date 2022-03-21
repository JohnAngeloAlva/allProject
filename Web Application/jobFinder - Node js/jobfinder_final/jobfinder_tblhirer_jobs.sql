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
-- Table structure for table `tblhirer_jobs`
--

DROP TABLE IF EXISTS `tblhirer_jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblhirer_jobs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `JobTitle` varchar(30) NOT NULL,
  `JobCategory` varchar(30) NOT NULL,
  `JobDescription` longtext NOT NULL,
  `JobRequirements` longtext NOT NULL,
  `Salary` varchar(30) NOT NULL,
  `JobNature` varchar(45) NOT NULL,
  `JobExperience` longtext NOT NULL,
  `Location` longtext NOT NULL,
  `CompanyName` mediumtext NOT NULL,
  `CompanyEmail` varchar(50) NOT NULL,
  `CompanyDescription` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhirer_jobs`
--

LOCK TABLES `tblhirer_jobs` WRITE;
/*!40000 ALTER TABLE `tblhirer_jobs` DISABLE KEYS */;
INSERT INTO `tblhirer_jobs` VALUES (1,'Web Developer edited','UI/UX developer edited','Heaven frucvitful doesn\'t cover lesser dvsays appear creeping seasons so behold','dsaaaaaaaaaaaaaaaaaa','123131-123123','Part-time','sadasdsad','Lucena City','qweqwe','wqeqeqeweqw','companyDescriptions'),(2,'Web Developer','UI/UX developer','Heaven frucvitful doesn\'t cover lesser dvsays appear creeping seasons so behold','dsaaaaaaaaaaaaaaaaaa','123131-123123','Full-time','sadasdsad','Lucena City','qweqwe','wqeqeqeweqw','heyheyhey'),(4,'System Development','asdadas','asdsad','asdasd','4655','asdsa','asdasda','asdasd','asdasd','asdasd','aeyyyyyyyyyyyy'),(5,'System Development','asdadas','asdsad','asdasd','4655','asdsa','asdasda','asdasd','asdasd','asdasd','asdasd'),(6,'Developer','Developer','Heaven frucvitful doesn\'t cover lesser dvsays appear creeping seasons so behold','College Diploma','5555-6666','PartTime','3 years','Tayabas City','colorlib','colorlib@gmail.com','Heaven frucvitful doesn\'t cover lesser dvsays appear creeping seasons so behold'),(8,'IT ','Developer','We are looking for an IT Operations Manager to oversee our company’s hardware, software and computer networks.','Maintain and optimize local company networks and servers\nBe responsible for device and password management\nOversee data backup and system security operations (e.g. user authorization, firewalls)\nManage installations, upgrades and configurations of hardware and software\nAssess system performance and recommend improvements\nResolve issues escalated by technicians and engineers\nEnsure data is handled, transferred or processed according to legal and company guidelines','44-85454','part-time','Proven experience as IT Operations Manager\nExperience with system installation, configuration and analysis\nThorough knowledge of networks and cloud computing\nKnowledge of data protection operations and legislation (e.g. GDPR)\nLeadership and organizational skills\nAbility to manage multiple projects\nOutstanding communication skills','Quezon City','colib','colib@gmail.com','IT Operations Manager responsibilities include monitoring network infrastructure and resolving system issues. You need to have experience with IT performance management, network administration and system security. If you’re also familiar with data protection regulations and able to juggle multiple projects, we’d like to meet you.'),(9,'IT Developer','Developer','sad','asd','44-85454','asd','asd','Tayabas City','asdasd','asda','asdad'),(10,'Web Developer edited','UI/UX developer','Heaven frucvitful doesn\'t cover lesser dvsays appear creeping seasons so behold','dsaaaaaaaaaaaaaaaaaa','123131-123123','Full-time','sadasdsad','Lucena City','qweqwe','wqeqeqeweqw','companyDescription'),(11,'Web Developer ','UI/UX developer','asda','asdasd','6456-4545','adwad','awdawd','Quezon City','awdawd','awdawd','dawdawdw');
/*!40000 ALTER TABLE `tblhirer_jobs` ENABLE KEYS */;
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
