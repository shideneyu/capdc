-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 23, 2013 at 03:05 PM
-- Server version: 5.5.29
-- PHP Version: 5.4.6-1ubuntu1.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ppe4`
--

-- --------------------------------------------------------

--
-- Table structure for table `especes`
--

CREATE TABLE IF NOT EXISTS `especes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `especes`
--

INSERT INTO `especes` (`id`, `libelle`) VALUES
(1, 'narcissus'),
(2, 'lotus');

-- --------------------------------------------------------

--
-- Table structure for table `parcelles`
--

CREATE TABLE IF NOT EXISTS `parcelles` (
  `idparcelle` int(11) NOT NULL AUTO_INCREMENT,
  `idexploitation` int(11) NOT NULL,
  `rendementprevu` varchar(16) NOT NULL,
  `rendementrealise` varchar(16) NOT NULL,
  `surface` varchar(16) NOT NULL,
  `especeid` int(11) NOT NULL,
  PRIMARY KEY (`idparcelle`,`idexploitation`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1545 ;

--
-- Dumping data for table `parcelles`
--

INSERT INTO `parcelles` (`idparcelle`, `idexploitation`, `rendementprevu`, `rendementrealise`, `surface`, `especeid`) VALUES
(1529, 2, '', '', '', 1),
(1528, 1, '', '', '', 1),
(1527, 1, '', '', '', 1),
(1526, 1, '', '', '', 1),
(1525, 1, '', '', '', 1),
(1524, 1, '', '', '', 1),
(1523, 1, '', '', '', 1),
(1522, 1, '', '', '', 1),
(1521, 1, '', '', '', 1),
(1520, 1, '', '', '', 1),
(1519, 1, '', '', '', 1),
(1518, 1, '', '', '', 1),
(1517, 1, '', '', '', 1),
(1516, 1, '', '', '', 1),
(234, 1, '', '', '', 1),
(1530, 1, '', '', '', 1),
(1531, 1, '332', '3232', '2323', 2),
(1532, 1, '', '', '', 1),
(1533, 1, '', '', '', 1),
(1534, 1, '', '', '', 1),
(1535, 1, '', '', '', 1),
(1536, 1, '', '', '', 1),
(1537, 1, '', '', '', 1),
(1538, 1, '', '', '', 1),
(1539, 1, '', '', '', 1),
(1540, 1, '', '', '', 1),
(1541, 1, '', '', '', 1),
(1542, 1, '', '', '', 1),
(1543, 2, '', '', '', 1),
(1544, 1, '', '', '', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
