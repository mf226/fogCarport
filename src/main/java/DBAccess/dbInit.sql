-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FogDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FogDB` DEFAULT CHARACTER SET latin1 ;
USE `FogDB` ;

-- -----------------------------------------------------
-- Table `FogDB`.`Materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`Materials` (
  `itemNumber` INT(11) NOT NULL AUTO_INCREMENT,
  `materialName` VARCHAR(45) NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `category` VARCHAR(45) NOT NULL,
  `amountInPackage` INT(11) NULL DEFAULT NULL,
  `stockLength` INT(11) NULL DEFAULT NULL,
  `inStock` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`itemNumber`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FogDB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`User` (
  `UserID` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `creationDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `UserID_UNIQUE` (`UserID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 88
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FogDB`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`Order` (
  `orderID` INT(11) NOT NULL AUTO_INCREMENT,
  `userID` INT(11) NOT NULL,
  `length` INT(11) NULL DEFAULT NULL,
  `width` INT(11) NULL DEFAULT NULL,
  `height` INT(11) NULL DEFAULT NULL,
  `angle` INT(11) NULL DEFAULT NULL,
  `roofType` INT(11) NULL DEFAULT NULL,
  `hasShed` TINYINT(4) NULL DEFAULT NULL,
  `shedLength` INT(11) NULL DEFAULT NULL,
  `shedWidth` INT(11) NULL DEFAULT NULL,
  `shedPlacement` VARCHAR(45) NULL DEFAULT NULL,
  `wallType` INT(11) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `orderDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`orderID`, `userID`),
  INDEX `fk_Order_User_idx` (`userID` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`userID`)
    REFERENCES `FogDB`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FogDB`.`OrderDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`OrderDetails` (
  `amount` INT(11) NOT NULL,
  `orderID` INT(11) NOT NULL,
  `itemNumber` INT(11) NOT NULL,
  PRIMARY KEY (`orderID`, `itemNumber`),
  INDEX `fk_OrderDetails_Materials_idx` (`itemNumber` ASC),
  CONSTRAINT `fk_OrderDetails_Materials`
    FOREIGN KEY (`itemNumber`)
    REFERENCES `FogDB`.`Materials` (`itemNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderDetails_Order`
    FOREIGN KEY (`orderID`)
    REFERENCES `FogDB`.`Order` (`orderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
