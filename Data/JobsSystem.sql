-- JobsSystem.sql

-- Schema JobsSystem
CREATE SCHEMA IF NOT EXISTS `JobsSystem` DEFAULT CHARACTER SET utf8;
USE `JobsSystem`;


-- Table Login
CREATE TABLE IF NOT EXISTS `JobsSystem`.`Login` 
(
  `idLogin`     INT           NOT NULL AUTO_INCREMENT,
  `email`       VARCHAR(45)   NULL,
  `password`    VARCHAR(20)   NULL,
  `type`        INT           NULL,
  `enable`      TINYINT       NOT NULL,


  PRIMARY KEY (`idLogin`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
);


-- Table Company
CREATE TABLE IF NOT EXISTS `JobsSystem`.`Company` 
(
  `idCompany`       INT           NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(45)   NULL,
  `description`     VARCHAR(300)   NULL,
  `phoneNumber`     VARCHAR(10)   NULL,
  `longitude`       DOUBLE        NULL,
  `latitude`        DOUBLE        NULL,
  `Login_idLogin`   INT           NOT NULL,


  PRIMARY KEY (`idCompany`),
  CONSTRAINT `fk_Company_Login`
  FOREIGN KEY (`Login_idLogin`)
  REFERENCES `JobsSystem`.`Login` (`idLogin`)
);


-- Table Position
CREATE TABLE IF NOT EXISTS `JobsSystem`.`Position` 
(
  `idPosition`          INT           NOT NULL AUTO_INCREMENT,
  `name`                VARCHAR(45)   NULL,
  `publishDate`         DATE          NULL,
  `salary`              DOUBLE        NULL,
  `publicPos`           TINYINT       NULL,
  `Company_idCompany`   INT           NOT NULL,
  `enable`      		TINYINT       NOT NULL,


  PRIMARY KEY (`idPosition`),
  CONSTRAINT `fk_Position_Company1`
  FOREIGN KEY (`Company_idCompany`)
  REFERENCES `JobsSystem`.`Company` (`idCompany`)
);


-- Table Offerer
CREATE TABLE IF NOT EXISTS `JobsSystem`.`Offerer` 
(
  `idOfferer`       INT   	      NOT NULL,
  `name`            VARCHAR(45)   NULL,
  `lastName`        VARCHAR(45)   NULL,
  `originCountry`   VARCHAR(45)   NULL,
  `phoneNumber`     VARCHAR(10)   NULL,
  `Login_idLogin`   INT           NOT NULL,


  PRIMARY KEY (`idOfferer`),
  CONSTRAINT `fk_Offerer_Login1`
  FOREIGN KEY (`Login_idLogin`)
  REFERENCES `JobsSystem`.`Login` (`idLogin`)
);


-- Table Feature
CREATE TABLE IF NOT EXISTS `JobsSystem`.`Feature` 
(
  `idFeature`           INT           NOT NULL AUTO_INCREMENT,
  `name`                VARCHAR(45)   NULL,
  `Feature_idFeature`   INT           NULL,


  PRIMARY KEY (`idFeature`),
  CONSTRAINT `fk_Feature_Feature1`
  FOREIGN KEY (`Feature_idFeature`)
  REFERENCES `JobsSystem`.`Feature` (`idFeature`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)
);


-- Table PositionFeature
CREATE TABLE IF NOT EXISTS `JobsSystem`.`PositionFeature` 
(
  `idPositionFeature`     INT     NOT NULL AUTO_INCREMENT,
  `level`                 FLOAT   NULL,
  `Position_idPosition`   INT     NOT NULL,
  `Feature_idFeature`     INT     NOT NULL, 


  PRIMARY KEY (`idPositionFeature`),
  CONSTRAINT `fk_PositionFeature_Position1`
  FOREIGN KEY (`Position_idPosition`)
  REFERENCES `JobsSystem`.`Position` (`idPosition`),
  CONSTRAINT `fk_PositionFeature_Feature1`
  FOREIGN KEY (`Feature_idFeature`)
  REFERENCES `JobsSystem`.`Feature` (`idFeature`),
  UNIQUE INDEX `feature_UNIQUE` (`Position_idPosition`,`Feature_idFeature`)
);


-- Table OffererFeature
CREATE TABLE IF NOT EXISTS `JobsSystem`.`OffererFeature` 
(
  `idOffererFeature`    INT           NOT NULL AUTO_INCREMENT,
  `level`               FLOAT         NULL,
  `Offerer_idOfferer`   INT           NOT NULL,
  `Feature_idFeature`   INT           NOT NULL,


  PRIMARY KEY (`idOffererFeature`),
  CONSTRAINT `fk_OffererFeature_Offerer1`
  FOREIGN KEY (`Offerer_idOfferer`)
  REFERENCES `JobsSystem`.`Offerer` (`idOfferer`),
  CONSTRAINT `fk_OffererFeature_Feature1`
  FOREIGN KEY (`Feature_idFeature`)
  REFERENCES `JobsSystem`.`Feature` (`idFeature`),
  UNIQUE INDEX `feature_UNIQUE` (`Offerer_idOfferer`,`Feature_idFeature`)
);


insert into login(email,password,type,enable) values ('admin@jobssystem.com','Pa$$word',3,127);







