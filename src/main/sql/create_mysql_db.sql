CREATE DATABASE IF NOT EXISTS `mydb`;
USE `mydb`;


DROP TABLE IF EXISTS `address`;


CREATE TABLE `address` (
  `Country`            VARCHAR(15)      DEFAULT NULL,
  `City`               VARCHAR(45)      DEFAULT NULL,
  `Street`             VARCHAR(20)      DEFAULT NULL,
  `House`              VARCHAR(7)       DEFAULT NULL,
  `Apartment`          VARCHAR(7)       DEFAULT NULL,
  `contacts_idcontact` INT(11) NOT NULL,
  `Postcode`           VARCHAR(15)      DEFAULT NULL,
  `idAddress`          INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idAddress`),
  KEY `fk_address_contacts1_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_address_contacts1` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `idattachment`       INT(11) NOT NULL AUTO_INCREMENT,
  `Name`               VARCHAR(255)     DEFAULT NULL,
  `DateUpload`         DATE    NOT NULL,
  `attachment_comment` TEXT,
  `contacts_idcontact` INT(11) NOT NULL,
  `FilePath`           VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`idattachment`),
  KEY `fk_attachment_contacts1_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_attachment_contacts1` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `idcontact`     INT(11)     NOT NULL           AUTO_INCREMENT,
  `Surname`       VARCHAR(45) NOT NULL,
  `FirstName`     VARCHAR(45) NOT NULL,
  `MiddleName`    VARCHAR(45)                    DEFAULT NULL,
  `BirthdayDate`  DATE                           DEFAULT NULL,
  `Gender`        ENUM ('', 'Male', 'Female')    DEFAULT NULL,
  `MaritalStatus` ENUM ('', 'Single', 'Married') DEFAULT NULL,
  `Website`       VARCHAR(45)                    DEFAULT NULL,
  `Email`         VARCHAR(60)                    DEFAULT NULL,
  `Workplace`     VARCHAR(45)                    DEFAULT NULL,
  `Citizenship`   VARCHAR(45)                    DEFAULT NULL,
  `UrlAvatar`     VARCHAR(255)                   DEFAULT NULL,
  PRIMARY KEY (`idcontact`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 52
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `genders`;
CREATE TABLE `genders` (
  `Gender` VARCHAR(15) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='all values of gender';


DROP TABLE IF EXISTS `maritals`;
CREATE TABLE `maritals` (
  `Marital` VARCHAR(10) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='all values of marital status';


DROP TABLE IF EXISTS `telephone`;
CREATE TABLE `telephone` (
  `idtelephone`        INT(11) NOT NULL AUTO_INCREMENT,
  `DialingPrefix`      INT(11)          DEFAULT NULL,
  `ProviderCode`       INT(11)          DEFAULT NULL,
  `PhoneNumber`        INT(11)          DEFAULT NULL,
  `commentary`         TEXT,
  `contacts_idcontact` INT(11) NOT NULL,
  `NumberType`         VARCHAR(15)      DEFAULT NULL,
  PRIMARY KEY (`idtelephone`),
  KEY `fk_telephones_contacts_idx` (`contacts_idcontact`),
  CONSTRAINT `fk_telephones_contacts` FOREIGN KEY (`contacts_idcontact`) REFERENCES `contact` (`idcontact`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;
