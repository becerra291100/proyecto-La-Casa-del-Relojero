-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.1.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyecto
CREATE DATABASE IF NOT EXISTS `proyecto` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `proyecto`;

-- Volcando estructura para tabla proyecto.login
CREATE TABLE IF NOT EXISTS `login` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(30) DEFAULT NULL,
  `contrasena` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `curp` varchar(18) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `municipio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `curp` (`curp`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyecto.login: ~19 rows (aproximadamente)
INSERT INTO `login` (`ID`, `usuario`, `contrasena`, `status`, `curp`, `estado`, `municipio`) VALUES
	(1, 'root', 'root', 1, NULL, NULL, NULL),
	(2, 'moises', '123', 1, NULL, NULL, NULL),
	(3, 'whiskas', 'whisky', 1, NULL, NULL, NULL),
	(4, 'nancicleta', 'nancy', 2, NULL, NULL, NULL),
	(5, 'ceci', 'ceci', 2, NULL, NULL, NULL),
	(6, 'marco', 'marco123', 1, NULL, NULL, NULL),
	(7, 'cecii', 'ceci', 2, NULL, NULL, NULL),
	(9, 'Moises Alejandro Gómez Rubio', 'CUT', 2, '1', NULL, NULL),
	(10, 'niobe', 'niobe', 3, NULL, NULL, NULL),
	(11, 'becerra', 'becerra', 2, NULL, NULL, NULL),
	(12, 'becerra2', 'POLLOLOCO', 2, NULL, NULL, NULL),
	(13, 'BECERRA22', 'HHHHH', 2, 'GORM030114HJCMBSA1', NULL, NULL),
	(17, 'marco222', 'HHHHH', 2, 'GORM030114HJCMBSA2', NULL, NULL),
	(20, 'marco2223', 'HHHHH', 2, 'GORM030114HJCMBSA3', NULL, NULL),
	(21, 'noe salvador hernandez', 'noe', 2, 'GORM030114HJCMBSA5', '1', NULL),
	(22, 'noe salvador nada', 'noe', 2, 'GORM030114HJCMBSA6', '1', NULL),
	(23, 'user', 'user', 2, 'curp', NULL, NULL),
	(24, 'noe salvador nada', 'none', 2, 'GORM030114HJCMBS66', '1', NULL),
	(25, 'noe salvador nada nado', 'clase', 2, 'GORM030114HJCMBS68', '1', NULL);

-- Volcando estructura para tabla proyecto.quejas
CREATE TABLE IF NOT EXISTS `quejas` (
  `curp` varchar(50) DEFAULT NULL,
  `queja` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyecto.quejas: ~6 rows (aproximadamente)
INSERT INTO `quejas` (`curp`, `queja`) VALUES
	(NULL, 'hola'),
	(NULL, 'NADA'),
	(NULL, 'hola, no tengo quejas'),
	('curp', 'queja mala'),
	('curp', 'hola?\r\n'),
	('curp', 'hola? jaja');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
loginmysqlproyectoquejasloginloginla_casa_del_relojerola_casa_del_relojerousuariousuariola_casa_del_relojerola_casa_del_relojerousuariousuariousuario