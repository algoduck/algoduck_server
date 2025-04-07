CREATE DATABASE  IF NOT EXISTS `algoduck_local_dev` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `algoduck_local_dev`;
-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: algoduck_local_dev
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
-- Table structure for table `algorithms`
--

DROP TABLE IF EXISTS `algorithms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithms` (
                              `problem_count` int NOT NULL,
                              `algorithm_id` bigint NOT NULL AUTO_INCREMENT,
                              `created_at` datetime(6) DEFAULT NULL,
                              `modified_at` datetime(6) DEFAULT NULL,
                              `algorithm_name` varchar(255) NOT NULL,
                              PRIMARY KEY (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `algorithms_seq`
--

DROP TABLE IF EXISTS `algorithms_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithms_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aliases`
--

DROP TABLE IF EXISTS `aliases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aliases` (
                           `algorithm_id` bigint NOT NULL,
                           `alias_id` bigint NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `alias_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           PRIMARY KEY (`alias_id`),
                           KEY `FKjd0agnlivtd4q4hxdkfv1k5cx` (`algorithm_id`),
                           CONSTRAINT `FKjd0agnlivtd4q4hxdkfv1k5cx` FOREIGN KEY (`algorithm_id`) REFERENCES `algorithms` (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aliases_seq`
--

DROP TABLE IF EXISTS `aliases_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aliases_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
                             `created_at` datetime(6) DEFAULT NULL,
                             `language_id` bigint NOT NULL AUTO_INCREMENT,
                             `modified_at` datetime(6) DEFAULT NULL,
                             `name` varchar(255) NOT NULL,
                             PRIMARY KEY (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `languages_seq`
--

DROP TABLE IF EXISTS `languages_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
                           `solved` int NOT NULL,
                           `created_at` datetime(6) DEFAULT NULL,
                           `member_id` bigint NOT NULL AUTO_INCREMENT,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `quit_request_time` datetime(6) DEFAULT NULL,
                           `login_id` varchar(20) NOT NULL,
                           `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                           `email` varchar(40) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `profile_image_url` text,
                           `status_message` varchar(255) DEFAULT NULL,
                           `member_status` enum('ACTIVE','DELETED','PENDING_DELETION','WAITING') NOT NULL,
                           `role` enum('ADMINISTRATOR','GENERAL','INSRTUCTOR') NOT NULL,
                           PRIMARY KEY (`member_id`),
                           UNIQUE KEY `UKlq5wej6688i1bd6b5c11neptj` (`login_id`),
                           UNIQUE KEY `UKe6u9u9ypoc7oldnpxdjwcdx3` (`nickname`),
                           UNIQUE KEY `UK9d30a9u1qpg8eou0otgkwrp5d` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `members_seq`
--

DROP TABLE IF EXISTS `members_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problem_images`
--

DROP TABLE IF EXISTS `problem_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem_images` (
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `modified_at` datetime(6) DEFAULT NULL,
                                  `problem_id` bigint NOT NULL,
                                  `problem_image_id` bigint NOT NULL AUTO_INCREMENT,
                                  `problem_image_name` varchar(255) NOT NULL,
                                  `problem_image_url` text NOT NULL,
                                  PRIMARY KEY (`problem_image_id`),
                                  KEY `FK8xvmxm0kyntcniom2kdvhjdn7` (`problem_id`),
                                  CONSTRAINT `FK8xvmxm0kyntcniom2kdvhjdn7` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problem_images_seq`
--

DROP TABLE IF EXISTS `problem_images_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem_images_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problems`
--

DROP TABLE IF EXISTS `problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems` (
                            `difficulty` int NOT NULL,
                            `created_at` datetime(6) DEFAULT NULL,
                            `modified_at` datetime(6) DEFAULT NULL,
                            `problem_id` bigint NOT NULL AUTO_INCREMENT,
                            `description` text NOT NULL,
                            `input_description` text NOT NULL,
                            `output_description` text NOT NULL,
                            `problem_number` varchar(255) NOT NULL,
                            `title` varchar(255) NOT NULL,
                            PRIMARY KEY (`problem_id`),
                            UNIQUE KEY `UKrx2o7ywheuukoat9j590erwgb` (`problem_number`),
                            KEY `idx_difficulty` (`difficulty`),
                            FULLTEXT KEY `ft_title_description` (`title`,`description`) /*!50100 WITH PARSER `ngram` */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problems_algorithms`
--

DROP TABLE IF EXISTS `problems_algorithms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems_algorithms` (
                                       `algorithm_id` bigint NOT NULL,
                                       `created_at` datetime(6) DEFAULT NULL,
                                       `modified_at` datetime(6) DEFAULT NULL,
                                       `problem_algorithm_id` bigint NOT NULL AUTO_INCREMENT,
                                       `problem_id` bigint NOT NULL,
                                       PRIMARY KEY (`problem_algorithm_id`),
                                       KEY `FKedidxhfamyec01xh6t0tsinsh` (`algorithm_id`),
                                       KEY `FK23w7shis2jfcihe3g4pmp552b` (`problem_id`),
                                       CONSTRAINT `FK23w7shis2jfcihe3g4pmp552b` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`),
                                       CONSTRAINT `FKedidxhfamyec01xh6t0tsinsh` FOREIGN KEY (`algorithm_id`) REFERENCES `algorithms` (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problems_algorithms_seq`
--

DROP TABLE IF EXISTS `problems_algorithms_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems_algorithms_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `problems_seq`
--

DROP TABLE IF EXISTS `problems_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submissions` (
                               `execution_time` int NOT NULL,
                               `memory_usage` int NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `member_id` bigint NOT NULL,
                               `modified_at` datetime(6) DEFAULT NULL,
                               `problem_id` bigint NOT NULL,
                               `submission_id` bigint NOT NULL AUTO_INCREMENT,
                               `version_id` bigint NOT NULL,
                               `code_name` varchar(255) NOT NULL,
                               `code_url` text NOT NULL,
                               `status` enum('ACCEPTED','COMPILE_ERROR','JUDGING','MEMORY_LIMIT_EXCEEDED','RUNTIME_ERROR','TIME_LIMIT_EXCEEDED','WRONG_ANSWER') NOT NULL,
                               PRIMARY KEY (`submission_id`),
                               UNIQUE KEY `UK5kqts7r3rd33i3ufjaytu1wok` (`version_id`),
                               KEY `FKq08lqmradv74odwjej8rp5i0w` (`member_id`),
                               KEY `FKj5kbdqokftgx992cx24x3s583` (`problem_id`),
                               CONSTRAINT `FK7ye47qxirx9u9lbptfkpx1ehj` FOREIGN KEY (`version_id`) REFERENCES `versions` (`version_id`),
                               CONSTRAINT `FKj5kbdqokftgx992cx24x3s583` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`),
                               CONSTRAINT `FKq08lqmradv74odwjej8rp5i0w` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submissions_seq`
--

DROP TABLE IF EXISTS `submissions_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submissions_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testcases`
--

DROP TABLE IF EXISTS `testcases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testcases` (
                             `is_public` bit(1) NOT NULL,
                             `created_at` datetime(6) DEFAULT NULL,
                             `modified_at` datetime(6) DEFAULT NULL,
                             `problem_id` bigint NOT NULL,
                             `testcase_id` bigint NOT NULL AUTO_INCREMENT,
                             `testcase_input_name` varchar(255) NOT NULL,
                             `testcase_input_url` text NOT NULL,
                             `testcase_output_name` varchar(255) NOT NULL,
                             `testcase_output_url` text NOT NULL,
                             PRIMARY KEY (`testcase_id`),
                             KEY `FKme0641jfjwft3ryqgkcepbf12` (`problem_id`),
                             CONSTRAINT `FKme0641jfjwft3ryqgkcepbf12` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testcases_seq`
--

DROP TABLE IF EXISTS `testcases_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testcases_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `versions`
--

DROP TABLE IF EXISTS `versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `versions` (
                            `created_at` datetime(6) DEFAULT NULL,
                            `language_id` bigint NOT NULL,
                            `modified_at` datetime(6) DEFAULT NULL,
                            `version_id` bigint NOT NULL AUTO_INCREMENT,
                            `version_name` varchar(255) NOT NULL,
                            PRIMARY KEY (`version_id`),
                            KEY `FKgjwbbtga3wqe92watcq07h4k2` (`language_id`),
                            CONSTRAINT `FKgjwbbtga3wqe92watcq07h4k2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `versions_seq`
--

DROP TABLE IF EXISTS `versions_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `versions_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'algoduck_local_dev'
--

--
-- Dumping routines for database 'algoduck_local_dev'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-22 22:55:20
