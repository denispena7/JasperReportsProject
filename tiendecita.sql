-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendecitadpm
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulos` (
  `idArticulo` int NOT NULL AUTO_INCREMENT,
  `descripcionArticulo` varchar(45) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `precioArticulo` decimal(6,2) NOT NULL,
  `stockArticulo` int NOT NULL,
  PRIMARY KEY (`idArticulo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (1,'Coca Cola Normal',0.89,24),(2,'Nestea Limón',0.89,32),(3,'Aquarius Naranja',0.89,32),(4,'Fanta Sandía',0.89,64),(5,'Pepsi Zero',0.79,32),(6,'Powerade',1.45,24),(7,'Monster',1.79,32),(8,'Eneryeti',1.09,32),(9,'Chameleon',0.49,96),(10,'Red Bull',1.99,24),(11,'Fanta Frambuesa',0.89,24),(14,'Fanta Limón Zero',0.89,16),(19,'Fuze Tea Limón',0.99,24);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_tickets`
--

DROP TABLE IF EXISTS `detalles_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_tickets` (
  `idTicketFK` int NOT NULL,
  `idArticuloFK` int NOT NULL,
  `cantidadArticulo` int NOT NULL,
  PRIMARY KEY (`idTicketFK`,`idArticuloFK`),
  KEY `idArticuloFK_idx` (`idArticuloFK`),
  CONSTRAINT `idArticuloFK` FOREIGN KEY (`idArticuloFK`) REFERENCES `articulos` (`idArticulo`),
  CONSTRAINT `idTicketFK` FOREIGN KEY (`idTicketFK`) REFERENCES `tickets` (`idTicket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_tickets`
--

LOCK TABLES `detalles_tickets` WRITE;
/*!40000 ALTER TABLE `detalles_tickets` DISABLE KEYS */;
INSERT INTO `detalles_tickets` VALUES (1,1,3),(1,2,2),(1,3,1),(1,6,1),(1,9,2),(1,10,3),(1,11,1),(1,14,5),(2,7,3),(3,8,2),(5,1,7),(5,2,8),(5,3,3),(5,5,1),(5,8,1),(5,10,1),(5,14,1),(6,1,24),(7,2,8),(8,4,1),(8,11,1),(8,14,1);
/*!40000 ALTER TABLE `detalles_tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `fechaTicket` date NOT NULL,
  PRIMARY KEY (`idTicket`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,'2024-11-20'),(2,'2024-11-21'),(3,'2024-12-02'),(4,'2024-12-20'),(5,'2024-12-24'),(6,'2024-12-31'),(7,'2025-01-02'),(8,'2025-02-12');
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-13  3:56:21
