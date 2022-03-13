-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travel_agency
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travel_agency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_agency` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `travel_agency` ;

-- -----------------------------------------------------
-- Table `travel_agency`.`destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`destination` (
  `id` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `popularityLevel` INT NULL DEFAULT NULL,
  `ratingStars` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_pn5r81p16mxfwh2x3ebpucfb8` (`location` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`user` (
  `id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `firstName` VARCHAR(255) NULL DEFAULT NULL,
  `lastName` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`vacation_package`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`vacation_package` (
  `id` VARCHAR(255) NOT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  `extraSpecifications` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `peopleCapacity` INT NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `startDate` DATETIME NULL DEFAULT NULL,
  `destination_id` VARCHAR(255) NULL DEFAULT NULL,
  `bookings` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_n2rsnvkhj0htw07rn9mxn3j6` (`name` ASC) VISIBLE,
  INDEX `FKdxupp03nu120q45ihtophscyw` (`destination_id` ASC) VISIBLE,
  CONSTRAINT `FKdxupp03nu120q45ihtophscyw`
    FOREIGN KEY (`destination_id`)
    REFERENCES `travel_agency`.`destination` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `travel_agency`.`user_package`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency`.`user_package` (
  `user_id` VARCHAR(255) NOT NULL,
  `package_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`, `package_id`),
  INDEX `FK3ctgldfb9o04ep0opa037260r` (`package_id` ASC) VISIBLE,
  CONSTRAINT `FK3ctgldfb9o04ep0opa037260r`
    FOREIGN KEY (`package_id`)
    REFERENCES `travel_agency`.`vacation_package` (`id`),
  CONSTRAINT `FK8n8qhfs5eceli4n13yf6u1agp`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agency`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
