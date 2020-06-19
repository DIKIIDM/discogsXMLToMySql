-- MySQL dump 10.13  Distrib 5.6.47, for Linux (x86_64)
--
-- Host: localhost    Database: discogs
-- ------------------------------------------------------
-- Server version	5.6.47

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
-- Table structure for table `dc_artist`
--

DROP TABLE IF EXISTS `dc_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_artist` (
  `id` int(11) NOT NULL,
  `idDC` int(11) DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sRealName` varchar(1000) DEFAULT NULL,
  `sNameShort` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artist_sNameShort_index` (`sNameShort`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_artistalias`
--

DROP TABLE IF EXISTS `dc_artistalias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_artistalias` (
  `id` int(11) NOT NULL,
  `idArtist` int(11) DEFAULT NULL,
  `idDC` int(11) DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `idArtistDC` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artistalias_dc_artist_id_fk` (`idArtist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_artistvariation`
--

DROP TABLE IF EXISTS `dc_artistvariation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_artistvariation` (
  `id` int(11) NOT NULL,
  `idArtistDC` int(11) DEFAULT NULL,
  `idArtist` int(11) DEFAULT NULL,
  `sName` varchar(1000) DEFAULT NULL,
  `sNameShort` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dc_artistvariation_dc_artist_id_fk` (`idArtist`),
  KEY `dc_artistvariation_sNameShort_index` (`sNameShort`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_release`
--

DROP TABLE IF EXISTS `dc_release`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_release` (
  `id` int(11) NOT NULL,
  `idDC` int(11) DEFAULT NULL,
  `nYear` int(11) DEFAULT NULL,
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

DROP TABLE IF EXISTS `dc_releaseartist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_releaseartist` (
  `id` int(11) NOT NULL,
  `idArtistDC` int(11) DEFAULT NULL,
  `idRelease` int(11) DEFAULT NULL,
  `idReleaseDC` int(11) DEFAULT NULL,
  `idTrack` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releaseextraartist`
--

DROP TABLE IF EXISTS `dc_releaseextraartist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_releaseextraartist` (
  `id` int(11) NOT NULL,
  `idArtistDC` int(11) DEFAULT NULL,
  `idRelease` int(11) DEFAULT NULL,
  `idReleaseDC` int(11) DEFAULT NULL,
  `idTrack` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasegenre`
--

DROP TABLE IF EXISTS `dc_releasegenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_releasegenre` (
  `id` int(11) NOT NULL,
  `idRelease` int(11) DEFAULT NULL,
  `idReleaseDC` int(11) DEFAULT NULL,
  `sName` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseGenre_id_uindex` (`id`),
  KEY `dc_releasegenre_idRelease_index` (`idRelease`),
  KEY `dc_releasegenre_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasestyle`
--

DROP TABLE IF EXISTS `dc_releasestyle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_releasestyle` (
  `id` int(11) NOT NULL,
  `idRelease` int(11) DEFAULT NULL,
  `idReleaseDC` int(11) DEFAULT NULL,
  `sName` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseStyle_id_uindex` (`id`),
  KEY `dc_releasestyle_idRelease_index` (`idRelease`),
  KEY `dc_releasestyle_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dc_releasetrack`
--

DROP TABLE IF EXISTS `dc_releasetrack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dc_releasetrack` (
  `id` int(11) NOT NULL,
  `idRelease` int(11) DEFAULT NULL,
  `idReleaseDC` int(11) DEFAULT NULL,
  `sTitle` varchar(1000) DEFAULT NULL,
  `sType` varchar(1000) DEFAULT NULL,
  `sPosition` varchar(1000) DEFAULT NULL,
  `sDuration` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dc_releaseTrack_id_uindex` (`id`),
  KEY `dc_releasetrack_idRelease_index` (`idRelease`),
  KEY `dc_releasetrack_idReleaseDC_index` (`idReleaseDC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-01 13:08:21
