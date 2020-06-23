-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: discogs
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `dc_artist`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_artist` (
  `id` int NOT NULL,
  `idDC` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sRealName` varchar(1000) DEFAULT NULL,
  `sNameShort` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artist_sNameShort_index` (`sNameShort`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_artistalias`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_artistalias` (
  `id` int NOT NULL,
  `idArtist` int DEFAULT NULL,
  `idDC` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `idArtistDC` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artistalias_dc_artist_id_fk` (`idArtist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_artistvariation`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_artistvariation` (
  `id` int NOT NULL,
  `idArtistDC` int DEFAULT NULL,
  `idArtist` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sNameShort` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artistvariation_dc_artist_id_fk` (`idArtist`),
  KEY `dc_artistvariation_sNameShort_index` (`sNameShort`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_format`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_format` (
  `id` int NOT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `DC_Format_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_genre`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_genre` (
  `id` int NOT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_genre_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_label`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_label` (
  `id` int NOT NULL,
  `idDC` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_label_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_release`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_release` (
  `id` int NOT NULL,
  `idDC` int DEFAULT NULL,
  `nYear` int DEFAULT NULL,
  `sTitle` varchar(1000) DEFAULT NULL,
  `sReleased` varchar(1000) DEFAULT NULL,
  `sCountry` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_release_id_uindex` (`id`),
  KEY `dc_release_idDC_index` (`idDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releaseartist`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releaseartist` (
  `id` int NOT NULL,
  `idArtist` int DEFAULT NULL,
  `idArtistDC` int DEFAULT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `idTrack` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sAnv` varchar(1000) DEFAULT NULL,
  `sJoin` varchar(1000) DEFAULT NULL,
  `sRole` varchar(1000) DEFAULT NULL,
  `sTracks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseArtist_id_uindex` (`id`),
  KEY `dc_releaseartist_idArtistDC_index` (`idArtistDC`),
  KEY `dc_releaseartist_idRelease_index` (`idRelease`),
  KEY `dc_releaseartist_idTrack_index` (`idTrack`),
  KEY `dc_releaseartist_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releaseextraartist`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releaseextraartist` (
  `id` int NOT NULL,
  `idArtist` int DEFAULT NULL,
  `idArtistDC` int DEFAULT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `idTrack` int DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sAnv` varchar(1000) DEFAULT NULL,
  `sJoin` varchar(1000) DEFAULT NULL,
  `sRole` varchar(1000) DEFAULT NULL,
  `sTracks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseExtraArtist_id_uindex` (`id`),
  KEY `dc_releaseextraartist_idArtistDC_index` (`idArtistDC`),
  KEY `dc_releaseextraartist_idRelease_index` (`idRelease`),
  KEY `dc_releaseextraartist_idTrack_index` (`idTrack`),
  KEY `dc_releaseextraartist_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releaseformat`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releaseformat` (
  `id` int NOT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `idFormat` int DEFAULT NULL,
  `nQty` int DEFAULT NULL,
  `sText` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `DC_ReleaseFormat_id_uindex` (`id`),
  KEY `DC_ReleaseFormat_dc_release_id_fk` (`idRelease`),
  KEY `DC_ReleaseFormat_dc_format_id_fk` (`idFormat`),
  CONSTRAINT `DC_ReleaseFormat_dc_format_id_fk` FOREIGN KEY (`idFormat`) REFERENCES `dc_format` (`id`),
  CONSTRAINT `DC_ReleaseFormat_dc_release_id_fk` FOREIGN KEY (`idRelease`) REFERENCES `dc_release` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasegenre`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releasegenre` (
  `id` int NOT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `idGenre` int DEFAULT NULL,
  `sName` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseGenre_id_uindex` (`id`),
  KEY `dc_releasegenre_idRelease_index` (`idRelease`),
  KEY `dc_releasegenre_idReleaseDC_index` (`idReleaseDC`),
  KEY `dc_releasegenre_dc_genre_id_fk` (`idGenre`),
  CONSTRAINT `dc_releasegenre_dc_genre_id_fk` FOREIGN KEY (`idGenre`) REFERENCES `dc_genre` (`id`),
  CONSTRAINT `dc_releasegenre_dc_release_id_fk` FOREIGN KEY (`idRelease`) REFERENCES `dc_release` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releaselabel`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releaselabel` (
  `id` int NOT NULL,
  `idLabel` int DEFAULT NULL,
  `idLabelDC` int DEFAULT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `sCatno` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseLabel_id_uindex` (`id`),
  KEY `dc_releaselabel_dc_release_id_fk` (`idRelease`),
  KEY `dc_releaselabel_dc_label_id_fk` (`idLabel`),
  KEY `dc_releaselabel_sCatno_index` (`sCatno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasestyle`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releasestyle` (
  `id` int NOT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `idStyle` int DEFAULT NULL,
  `sName` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseStyle_id_uindex` (`id`),
  KEY `dc_releasestyle_idRelease_index` (`idRelease`),
  KEY `dc_releasestyle_idReleaseDC_index` (`idReleaseDC`),
  KEY `dc_releasestyle_dc_style_id_fk` (`idStyle`),
  CONSTRAINT `dc_releasestyle_dc_release_id_fk` FOREIGN KEY (`idRelease`) REFERENCES `dc_release` (`id`),
  CONSTRAINT `dc_releasestyle_dc_style_id_fk` FOREIGN KEY (`idStyle`) REFERENCES `dc_style` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasetrack`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_releasetrack` (
  `id` int NOT NULL,
  `idRelease` int DEFAULT NULL,
  `idReleaseDC` int DEFAULT NULL,
  `sTitle` varchar(1000) DEFAULT NULL,
  `sType` varchar(1000) DEFAULT NULL,
  `sPosition` varchar(1000) DEFAULT NULL,
  `sDuration` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseTrack_id_uindex` (`id`),
  KEY `dc_releasetrack_idRelease_index` (`idRelease`),
  KEY `dc_releasetrack_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_style`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dc_style` (
  `id` int NOT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `DC_Style_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `new_view`
--

SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `new_view` AS SELECT
 1 AS `idRelease`,
 1 AS `sTitle`,
 1 AS `nYear`,
 1 AS `sReleased`,
 1 AS `sCountry`,
 1 AS `sName`,
 1 AS `sJoin`,
 1 AS `sAnv`,
 1 AS `sRole`,
 1 AS `sTrackTitle`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `new_view`
--

/*!50001 DROP VIEW IF EXISTS `new_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `new_view` AS select `r`.`id` AS `idRelease`,`r`.`sTitle` AS `sTitle`,`r`.`nYear` AS `nYear`,`r`.`sReleased` AS `sReleased`,`r`.`sCountry` AS `sCountry`,`a`.`sName` AS `sName`,`ra`.`sJoin` AS `sJoin`,`ra`.`sAnv` AS `sAnv`,`ra`.`sRole` AS `sRole`,`rt`.`sTitle` AS `sTrackTitle` from (((`dc_release` `r` left join `dc_releaseartist` `ra` on((`ra`.`idRelease` = `r`.`id`))) left join `dc_artist` `a` on((`a`.`id` = `ra`.`idArtist`))) left join `dc_releasetrack` `rt` on(((`rt`.`idRelease` = `r`.`id`) and (`rt`.`id` = `ra`.`idTrack`)))) where (1 = 1) union all select `r`.`id` AS `id`,`r`.`sTitle` AS `sTitle`,`r`.`nYear` AS `nYear`,`r`.`sReleased` AS `sReleased`,`r`.`sCountry` AS `sCountry`,`a`.`sName` AS `sName`,`rea`.`sJoin` AS `sJoin`,`rea`.`sAnv` AS `sAnv`,`rea`.`sRole` AS `sRole`,`rt`.`sTitle` AS `sTitle` from (((`dc_release` `r` left join `dc_releaseextraartist` `rea` on((`rea`.`idRelease` = `r`.`id`))) left join `dc_artist` `a` on((`a`.`id` = `rea`.`idArtist`))) left join `dc_releasetrack` `rt` on(((`rt`.`idRelease` = `r`.`id`) and (`rt`.`id` = `rea`.`idTrack`)))) where (1 = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 22:20:20
