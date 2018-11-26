SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FogDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `FogDB` ;

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
  PRIMARY KEY (`itemNumber`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `FogDB`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FogDB`.`Order` (
  `orderID` INT(11) NOT NULL AUTO_INCREMENT,
  `userID` INT(11) NOT NULL,
  `length` INT(11) NULL,
  `width` INT(11) NULL,
  `height` INT(11) NULL,
  `angle` INT(11) NULL,
  `finalizedPrice` DOUBLE NULL DEFAULT NULL,
  `orderDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `status` ENUM('pending', 'approved', 'shipped') NULL,
  PRIMARY KEY (`orderID`, `userID`),
  INDEX `fk_Order_User_idx` (`userID` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`userID`)
    REFERENCES `FogDB`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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

INSERT INTO `FogDB`.`Materials` (`itemNumber`, `materialName`, `unit`, `price`, `category`) VALUES ('1', '45x195', 'm', '100', 'Træ'),
('2', '97x97', 'm', '90', 'Træ'),
('3', '4,5x60 mm skruer á 200 stk', 'stk', '300', 'Skruer'), 
('4', 'Vinkelbeslag 35', 'stk', '35', 'Beslag'),
('5', 'Plastmo	Ecolite	blåtonet', 'kvm', '100', 'Tag'),
('6', 'Røde tegl', 'stk', '25', 'Tagbelægning'),
('7', '25x150', 'm', '60', 'Beklædning'),
('8', 'Cement 10 kg', 'kg', '80', 'Andet'),
('9', 'Sort glaserede tegl', 'stk', '55', 'Tagbeklædning'),
('10', 'Plastmo	Ecolite	klar', 'stk', '52', 'Tag');
INSERT INTO `FogDB`.`User` (`UserID`, `email`, `password`, `role`) VALUES ('1', 'testuser@test.com', 'test', 'Customer'),
('2', 'admin', 'admin', 'ADMIN');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
