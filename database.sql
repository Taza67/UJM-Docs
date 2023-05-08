-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.29 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour udoc
DROP DATABASE IF EXISTS `udoc`;
CREATE DATABASE IF NOT EXISTS `udoc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `udoc`;

-- Listage de la structure de table udoc. collaborateurs
DROP TABLE IF EXISTS `collaborateurs`;
CREATE TABLE IF NOT EXISTS `collaborateurs` (
  `DOC` int unsigned NOT NULL,
  `USER` int unsigned NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `ID_DOC` (`DOC`),
  KEY `ID_USER` (`USER`),
  CONSTRAINT `ID_DOC` FOREIGN KEY (`DOC`) REFERENCES `documents` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ID_USER` FOREIGN KEY (`USER`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table udoc.collaborateurs : ~1 rows (environ)
INSERT INTO `collaborateurs` (`DOC`, `USER`, `ID`) VALUES
	(3, 1, 1);

-- Listage de la structure de table udoc. documents
DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int unsigned NOT NULL,
  `date_de_modification` date DEFAULT NULL,
  `chemin` varchar(255) DEFAULT NULL,
  `nom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table udoc.documents : ~4 rows (environ)
INSERT INTO `documents` (`id`, `id_utilisateur`, `date_de_modification`, `chemin`, `nom`) VALUES
	(1, 1, '2023-05-05', 'C:/', 'Rapport'),
	(2, 1, '2023-05-06', 'C:/', 'Readme'),
	(3, 2, '2023-05-07', 'C:/Documents/', 'Recipe'),
	(4, 2, '2023-05-08', 'C:/Téléchargements', 'Angular for noobs');

-- Listage de la structure de table udoc. utilisateur
DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table udoc.utilisateur : ~2 rows (environ)
INSERT INTO `utilisateur` (`id`, `pseudo`, `mot_de_passe`) VALUES
	(1, 'tom', 'tomlerat'),
	(2, 'Bibi', 'AngularEstLeMeilleurFrameWork');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
