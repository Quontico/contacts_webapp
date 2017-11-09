CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;


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


DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `Country` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `Street` varchar(255) DEFAULT NULL,
  `House` varchar(25) DEFAULT NULL,
  `Apartment` varchar(25) DEFAULT NULL,
  `contacts_idcontact` int(11) NOT NULL,
  `Postcode` varchar(45) DEFAULT NULL,
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idAddress`),
  KEY `fk_address_contacts1_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_address_contacts1` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `address` WRITE;
INSERT INTO `address` VALUES ('Belarus','Minsk','Germanovskaya','7','24',23,'2135483',2),('Russia','Kazan','Molotova',NULL,NULL,24,NULL,3),('Spain','Valencia','El Vega','45','3',25,'3526577',4),('Great Britain','Liverpool','Evengarden','1245','1',26,'184236545',5),('Great Britain','Stratford-upon-Avon','Shakespeare','3624','2',27,'164728459',6),(NULL,NULL,NULL,NULL,NULL,38,NULL,16),('htdy','gfhjv','vbhjf','4','5',43,'45615',21),('Беларусь','Солигорск','','','',45,'223710',23),('','','','','',49,'',27),('USA','New York','Random Street','8','9',52,'18432647',32),(NULL,NULL,NULL,NULL,NULL,55,NULL,35),(NULL,NULL,NULL,NULL,NULL,56,NULL,36),(NULL,NULL,NULL,NULL,NULL,57,NULL,37),(NULL,NULL,NULL,NULL,NULL,58,NULL,38);
UNLOCK TABLES;


DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `idattachment` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `DateUpload` date NOT NULL,
  `attachment_comment` text,
  `contacts_idcontact` int(11) NOT NULL,
  `FilePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idattachment`),
  KEY `fk_attachment_contacts1_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_attachment_contacts1` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `attachment` WRITE;
INSERT INTO `attachment` VALUES (3,'PDF_3_JVM_options_cheatsheet.pdf','2017-11-01','JVM',23,'/attachments/23/PDF_3_JVM_options_cheatsheet.pdf'),(7,'PDF_2_JavaScript_CheatSheet_Large.pdf','2017-11-01','jhgjhg',43,'/attachments/43/PDF_2_JavaScript_CheatSheet_Large.pdf'),(8,'PDF_2_Best_practices_java_cheatsheet.pdf','2017-11-03','pdf',39,'/attachments/39/PDF_2_Best_practices_java_cheatsheet.pdf'),(9,'PDF_1_JavaScript_CheatSheet_Brief.pdf','2017-11-03','pdf',52,'/attachments/52/PDF_1_JavaScript_CheatSheet_Brief.pdf'),(10,'Otkaz.docx','2017-11-03','otkaz',52,'/attachments/52/Otkaz.docx'),(11,'Otkaz.docx','2017-11-07','otkaz',23,'/attachments/23/Otkaz.docx'),(12,'hj.png','2017-11-07','picture                           \r\n                        ',23,'/attachments/23/hj.png'),(13,'Kopia_diploma_ASOI_2018.doc','2017-11-07','copy',23,'/attachments/23/Kopia_diploma_ASOI_2018.doc'),(14,'png.png.txt','2017-11-07','img',23,'/attachments/23/png.png.txt'),(15,'PDF_1_JavaScript_CheatSheet_Brief.pdf','2017-11-07','pdf_file',23,'/attachments/23/PDF_1_JavaScript_CheatSheet_Brief.pdf'),(16,'images.png','2017-11-07','image',23,'/attachments/23/images.png');
UNLOCK TABLES;


DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `idcontact` int(11) NOT NULL AUTO_INCREMENT,
  `Surname` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `MiddleName` varchar(255) DEFAULT NULL,
  `BirthdayDate` date DEFAULT NULL,
  `Gender` enum('','Male','Female') DEFAULT NULL,
  `MaritalStatus` enum('','Single','Married') DEFAULT NULL,
  `Website` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Workplace` varchar(255) DEFAULT NULL,
  `Citizenship` varchar(255) DEFAULT NULL,
  `UrlAvatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcontact`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `contact` WRITE;
INSERT INTO `contact` VALUES (23,'Kent','Alexander','Joseph','1993-11-02','Male','Married','MId.com','glacier32123@gmail.com','MID','Republic of Belarus','/avatars/23.png'),(24,'Ivanov','Ivan','Ivanovich','1987-05-19','Male','Single','site','skater753@yandex.by','IBA','Russia',NULL),(25,'Martines','Gabriel','Huano',NULL,'Female','Single','mail.com','martines@gmail.com','DG','Spain',NULL),(26,'Landers','Will','Joseph','2017-10-10','Male','Married','WillLanders.com','mailwill@gmail.com','BTO','Great Britain',NULL),(27,'Lewis','David','James','1994-10-30','Male','Single','dlewis.com',NULL,'OPL','Great Britain',NULL),(28,'Nash','Mike','Russel',NULL,'Male','Single','none','mike_nash@gmail.com','Smithsonian Museum','USA',NULL),(38,'sgreg','atbrbvg','d',NULL,'Male','Single',NULL,NULL,NULL,NULL,NULL),(39,'g','f','h','1982-07-15','Male','Single',NULL,'xnsajkxj@g',NULL,NULL,NULL),(43,'Pavlov','Alexey','xzc',NULL,'Male','Single','jhf','g@mail.co','hj','hf',NULL),(45,'Сергеев','Сергей','Сергеевич','1983-06-08','Male','Single','','','Белкалий','Беларусь',NULL),(49,'weq','dsa','vbc','2000-11-05','Male','Single','','','','',NULL),(52,'Mayer','Jason','Bryan','1988-06-28','Male','Single','site1','myemail@mail.com','Some place','USA','/avatars/52.png'),(55,'LastName','Name',NULL,NULL,'Female','Married',NULL,NULL,NULL,NULL,NULL),(56,'b','a',NULL,NULL,'','',NULL,'glacier32123@g.com',NULL,NULL,NULL),(57,'Huston','Fred','Jackson','2016-02-09','Male','Married',NULL,NULL,NULL,NULL,NULL),(58,'c','d',NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `genders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genders` (
  `Gender` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='all values of gender';
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `genders` WRITE;
INSERT INTO `genders` VALUES (''),('Male'),('Female');
UNLOCK TABLES;


DROP TABLE IF EXISTS `maritals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maritals` (
  `Marital` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='all values of marital status';
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `maritals` WRITE;
INSERT INTO `maritals` VALUES (''),('Single'),('Married');
UNLOCK TABLES;


DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telephone` (
  `idtelephone`        int(11) NOT NULL AUTO_INCREMENT,
  `DialingPrefix`      int(11)          DEFAULT NULL,
  `ProviderCode`       int(11)          DEFAULT NULL,
  `PhoneNumber`        INT(50)          DEFAULT NULL,
  `commentary`         text,
  `contacts_idcontact` int(11) NOT NULL,
  `NumberType`         VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`idtelephone`),
  KEY `fk_telephones_contacts_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_telephones_contacts` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `telephone` WRITE;
INSERT INTO `telephone` VALUES (1,375,29,6338425,'com',23,'Mobil'),(3,375,29,7023824,'comm By Ilyaaa...',23,'Mobile'),(4,375,33,63348,'jgkj',43,'jkj'),(6,375,29,8752664,'Работа',45,'Мобильный'),(7,375,33,6638796,'commentary one',39,'mobile'),(8,375,33,6338423,'',52,'mobile'),(9,375,33,1234567,'rand',52,'home'),(10,375,29,1234567,'comm',23,'mob'),(11,375,33,6338425,'some_comm                            \r\n                        ',57,'mobile'),(12,375,29,2574633,'optional',57,'office');
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;