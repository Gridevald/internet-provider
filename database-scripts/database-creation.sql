-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema internet_provider
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema internet_provider
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `internet_provider` DEFAULT CHARACTER SET utf8 ;
USE `internet_provider` ;

-- -----------------------------------------------------
-- Table `internet_provider`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`plan` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number.',
  `name` VARCHAR(45) NOT NULL COMMENT 'Name.',
  `download_speed` INT NOT NULL COMMENT 'Download speed in Mbit.',
  `upload_speed` INT NOT NULL COMMENT 'Upload speed in Mbit.',
  `price` DECIMAL(6,2) NOT NULL COMMENT 'Price in BYN per month',
  `description` TEXT(500) NULL COMMENT 'Description.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
COMMENT = 'Tariff plan.';


-- -----------------------------------------------------
-- Table `internet_provider`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number.',
  `email` VARCHAR(45) NOT NULL COMMENT 'E-mail. Should be unique.',
  `password` CHAR(40) NOT NULL COMMENT 'Password hash by sha-1.',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name.',
  `middle_name` VARCHAR(45) NOT NULL COMMENT 'Middle name.',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name.',
  `contract` INT(9) UNSIGNED NULL COMMENT 'Index number of user\'s contract.',
  `balance` DECIMAL(6,2) NULL COMMENT 'Actual balance in BYN. Can be negative.',
  `is_blocked` TINYINT(1) NULL COMMENT 'Flag which shows if user is blocked.',
  `city` VARCHAR(45) NULL COMMENT 'City name.',
  `street` VARCHAR(45) NULL COMMENT 'Street name.',
  `building` VARCHAR(45) NULL COMMENT 'Building index.',
  `apartments` INT NULL COMMENT 'Apartments index.',
  `plan_id` INT UNSIGNED NOT NULL COMMENT 'Foreign key of Tariff plan.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_plan_idx` (`plan_id` ASC),
  UNIQUE INDEX `contract_UNIQUE` (`contract` ASC),
  CONSTRAINT `fk_user_plan`
    FOREIGN KEY (`plan_id`)
    REFERENCES `internet_provider`.`plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'User information. Contract and email are not a PK because of big length.';


-- -----------------------------------------------------
-- Table `internet_provider`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`admin` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number',
  `personnel_number` INT(5) UNSIGNED NOT NULL COMMENT 'Personnel number in organization.',
  `email` VARCHAR(45) NOT NULL COMMENT 'E-mail. Should be unique.',
  `password` CHAR(40) NOT NULL COMMENT 'Password hash by sha-1.',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name.',
  `middle_name` VARCHAR(45) NOT NULL COMMENT 'Middle name.',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name.',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Administrator information. There is no more information because it\'s not necessary for project and admin identification.';


-- -----------------------------------------------------
-- Table `internet_provider`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`payment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number.',
  `sum` DECIMAL(6,2) NOT NULL COMMENT 'Sum of payment in BYN. ',
  `date` DATE NOT NULL COMMENT 'Date.',
  `user_id` INT UNSIGNED NOT NULL COMMENT 'Index of user, which made this payment.',
  PRIMARY KEY (`id`),
  INDEX `fk_payment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_provider`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Payments of user.';


-- -----------------------------------------------------
-- Table `internet_provider`.`traffic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`traffic` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number.',
  `downloaded` DOUBLE NOT NULL COMMENT 'Value of incoming traffic per day in Mb.',
  `uploaded` DOUBLE NOT NULL COMMENT 'Value of outgoing traffic per day in Mb.',
  `date` DATE NOT NULL COMMENT 'Date.',
  `user_id` INT UNSIGNED NOT NULL COMMENT 'Index of user, which made this traffic.',
  PRIMARY KEY (`id`),
  INDEX `fk_traffic_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_traffic_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_provider`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Traffic per day';


-- -----------------------------------------------------
-- Table `internet_provider`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_provider`.`customer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Index number.',
  `email` VARCHAR(45) NOT NULL COMMENT 'E-mail. Should be unique.',
  `password` CHAR(40) NOT NULL COMMENT 'Password hash by sha-1.',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name.',
  `middle_name` VARCHAR(45) NOT NULL COMMENT 'Middle name.',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name.',
  `city` VARCHAR(45) NOT NULL COMMENT 'City name.',
  `street` VARCHAR(45) NOT NULL COMMENT 'Street name.',
  `building` VARCHAR(45) NOT NULL COMMENT 'Building index.',
  `apartments` VARCHAR(45) NOT NULL COMMENT 'Apartments index.',
  `plan_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_customer_plan1_idx` (`plan_id` ASC),
  CONSTRAINT `fk_customer_plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `internet_provider`.`plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Customer information.';

USE `internet_provider` ;

-- -----------------------------------------------------
--  routine1
-- -----------------------------------------------------

DELIMITER $$
USE `internet_provider`$$
$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
