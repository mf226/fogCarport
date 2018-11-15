SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FogDB
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `FogDB` ;

-- -----------------------------------------------------
-- Schema FogDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FogDB` DEFAULT CHARACTER SET latin1 ;

---------------------------------------------------
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
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FogDB`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`Order` (
  `orderID` INT(11) NOT NULL AUTO_INCREMENT,
  `orderDescription` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `User_UserID` INT(11) NOT NULL,
  `orderDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderID`, `User_UserID`),
  INDEX `fk_Order_User_idx` (`User_UserID` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`User_UserID`)
    REFERENCES `FogDB`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FogDB`.`Træ_og_Tagplader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`Materials` (
  `itemNumber` INT NOT NULL AUTO_INCREMENT,
  `materialName` VARCHAR(45) NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`itemNumber`))
ENGINE = InnoDB
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
  CONSTRAINT `fk_OrderDetails_Order`
    FOREIGN KEY (`orderID`)
    REFERENCES `FogDB`.`Order` (`orderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderDetails_Materials`
    FOREIGN KEY (`itemNumber`)
    REFERENCES `FogDB`.`Materials` (`itemNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

USE `FogDB` ;

INSERT INTO `FogDB`.`Materials` (`itemNumber`, `materialName`, `unit`, `price`) VALUES ('1', '45x195', 'm', '100');
INSERT INTO `FogDB`.`Materials` (`itemNumber`, `materialName`, `unit`, `price`) VALUES ('2', '97x97', 'm', '90');
INSERT INTO `FogDB`.`User` (`UserID`, `email`, `password`, `role`) VALUES ('1', 'testuser@test.com', 'test', 'Customer');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
