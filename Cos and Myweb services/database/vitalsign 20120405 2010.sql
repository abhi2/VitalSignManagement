-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.32-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ndorange
--

CREATE DATABASE IF NOT EXISTS ndorange;
USE ndorange;

--
-- Definition of table `addvitals`
--

DROP TABLE IF EXISTS `addvitals`;
CREATE TABLE `addvitals` (
  `vitalsignId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `partyId` varchar(45) NOT NULL,
  `encounterId` varchar(45) NOT NULL,
  `vitalType` varchar(45) NOT NULL,
  `vitalValue` varchar(45) NOT NULL,
  `unitOfMeasure` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `entereddate` date NOT NULL,
  `enteredtime` time NOT NULL,
  `authtoken` varchar(45) NOT NULL,
  PRIMARY KEY (`vitalsignId`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `addvitals`
--

/*!40000 ALTER TABLE `addvitals` DISABLE KEYS */;
INSERT INTO `addvitals` (`vitalsignId`,`partyId`,`encounterId`,`vitalType`,`vitalValue`,`unitOfMeasure`,`username`,`entereddate`,`enteredtime`,`authtoken`) VALUES 
 (192,'158656','16850','BP-D','143','mmHg','jaiwardan','2012-04-01','23:21:17','79c2b327-05a7-4e8d-9e57-0931d5b27535'),
 (193,'158656','16854','BP-D','143','mmHg','jaiwardan','2012-04-01','23:21:17','890b1e3d-31e5-451f-aeeb-455820775fb7'),
 (194,'158656','16855','BP-D','143','mmHg','jaiwardan','2012-04-01','23:21:17','5afd982e-66b0-4e2e-9714-a909422d1509'),
 (195,'158656','16856','BP-D','143','mmHg','jaiwardan','2012-04-01','23:21:17','59c083a5-6fc4-421d-aed3-29836848efb7'),
 (196,'158656','16857','Glucose','298','mmol/l','jaiwardan','2012-04-05','11:31:15','e42d95b5-541a-413b-bf84-04b2745551de'),
 (197,'158656','16858','height','180','cm','jaiwardan','2012-04-01','23:21:47','d0ba6d14-3c5b-470a-aa69-bf5ce14e9767'),
 (198,'158656','16861','BP-D','143','mmHg','jaiwardan','2012-04-01','23:21:17','1bb52ce6-f0cb-4e69-9fc9-23a0d5d6cc8d'),
 (199,'158656','16896','BP-D','120','mmHg','jaiwardan','2012-04-05','11:27:27','2a8b3aeb-cf65-4d41-985e-546969d2d8a2'),
 (200,'158656','16897','Glucose','298','mmol/l','jaiwardan','2012-04-05','11:31:15','2a8b3aeb-cf65-4d41-985e-546969d2d8a2'),
 (201,'158656','16975','weight','88','kg','jaiwardan','2012-04-08','06:55:00','e65b3904-8dba-42dc-9fea-e7f0a890fadd'),
 (202,'158656','16975','Temp','100','F','jaiwardan','2012-04-08','06:55:23','e65b3904-8dba-42dc-9fea-e7f0a890fadd'),
 (203,'158656','16989','waist','32','cm','jaiwardan','2012-04-09','08:08:11','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (204,'158656','16989','hip','32','cm','jaiwardan','2012-04-09','08:08:25','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (205,'158656','16989','height','43','cm','jaiwardan','2012-04-09','08:08:36','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (206,'158656','16989','Pain Scale','3',' ','jaiwardan','2012-04-09','08:08:46','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (207,'158656','16989','pulse','72','BPM','jaiwardan','2012-04-09','08:09:32','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (208,'158656','16989','Pulse Oximetry','32','%','jaiwardan','2012-04-09','08:09:42','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (209,'158656','16989','Temp','87','F','jaiwardan','2012-04-09','08:10:04','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (210,'158656','16989','weight','45','kg','jaiwardan','2012-04-09','08:10:15','6420f033-f283-4c22-b68d-6c5a1819c0db'),
 (211,'158656','17002','BP-S','32','mmHg','jaiwardan','2012-04-10','07:13:23','8bdde16c-d4b1-4c4c-91d5-87029bfad51e'),
 (212,'158656','17005','BP-D','23','mmHg','jaiwardan','2012-04-11','12:13:55','b4b54b9b-8717-4177-96d3-a75f7dda262e'),
 (213,'158656','17005','BP-S','67','mmHg','jaiwardan','2012-04-11','12:13:55','b4b54b9b-8717-4177-96d3-a75f7dda262e');
/*!40000 ALTER TABLE `addvitals` ENABLE KEYS */;


--
-- Definition of table `setthresholds`
--

DROP TABLE IF EXISTS `setthresholds`;
CREATE TABLE `setthresholds` (
  `vitaltype` varchar(45) NOT NULL,
  `pid` varchar(45) NOT NULL,
  `threshold` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `setthresholds`
--

/*!40000 ALTER TABLE `setthresholds` DISABLE KEYS */;
INSERT INTO `setthresholds` (`vitaltype`,`pid`,`threshold`) VALUES 
 ('Height','158656','133');
/*!40000 ALTER TABLE `setthresholds` ENABLE KEYS */;


--
-- Definition of table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE `userdetails` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `createdBy` varchar(45) NOT NULL,
  `creatorPassword` varchar(45) NOT NULL,
  `pid` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `mstatus` varchar(45) NOT NULL,
  `dob` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdetails`
--

/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`username`,`password`,`createdBy`,`creatorPassword`,`pid`,`status`,`gender`,`mstatus`,`dob`) VALUES 
 ('vicky','prajapati','C144964.U685','jmartin','158658','good','M','S','1990-11-05'),
 ('rebecca','holmes','C144964.U685','jmartin','158658','good','F','S','1976-01-27'),
 ('erica','holmes','C144964.U685','jmartin','158658','good','F','S','1976-01-27'),
 ('tom','cruise','C144964.U685','jmartin','158658','good','M','S','1976-01-27'),
 ('marcus','king','C144964.U685','jmartin','158658','good','M','S','1986-01-27'),
 ('divya','kotla','C144964.U685','jmartin','158658','good','F','S','1990-01-27'),
 ('abhishek','sachdeva','C144964.U685','jmartin','158786','good','M','S','1985-12-08'),
 ('jaiwardan','jai','C144964.U685','jmartin','158656','good','','',''),
 ('jayasree','jayasree','C144964.U685','jmartin','158655','good','','',''),
 ('pallavi','pallavi','C144964.U685','jmartin','158657','good','','',''),
 ('ramesh','ramesh','C144964.U685','jmartin','158792','good','M','S','1988-12-08'),
 ('sravannn','kanchi','C144964.U685','jmartin','158654','good','','',''),
 ('Test','tester','jmartin','jmartin','144974','','','',''),
 ('Tester','tester','jmartin','jmartin','145151','','','','');
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;


--
-- Definition of table `vitalsignthreshold`
--

DROP TABLE IF EXISTS `vitalsignthreshold`;
CREATE TABLE `vitalsignthreshold` (
  `pid` varchar(20) NOT NULL,
  `BP_D` varchar(10) DEFAULT NULL,
  `BP_S` varchar(10) DEFAULT NULL,
  `Glucose` varchar(10) DEFAULT NULL,
  `headCircumference` varchar(10) DEFAULT NULL,
  `headCircumference2` varchar(10) DEFAULT NULL,
  `height` varchar(10) DEFAULT NULL,
  `painscale` varchar(10) DEFAULT NULL,
  `PeakRespFlowRt` varchar(10) DEFAULT NULL,
  `PostRespFlowRt` varchar(10) DEFAULT NULL,
  `pulse` varchar(10) DEFAULT NULL,
  `pulseoxymetry` varchar(10) DEFAULT NULL,
  `respiration` varchar(10) DEFAULT NULL,
  `temparature` varchar(10) DEFAULT NULL,
  `weight` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vitalsignthreshold`
--

/*!40000 ALTER TABLE `vitalsignthreshold` DISABLE KEYS */;
INSERT INTO `vitalsignthreshold` (`pid`,`BP_D`,`BP_S`,`Glucose`,`headCircumference`,`headCircumference2`,`height`,`painscale`,`PeakRespFlowRt`,`PostRespFlowRt`,`pulse`,`pulseoxymetry`,`respiration`,`temparature`,`weight`) VALUES 
 ('144974','130','456','123','null','100','190','10','null','null','45','null','null','102','45');
/*!40000 ALTER TABLE `vitalsignthreshold` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
