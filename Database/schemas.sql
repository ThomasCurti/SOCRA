CREATE SCHEMA IF NOT EXISTS `profilesdb` DEFAULT CHARACTER SET utf8 ;
USE `profilesdb` ;

-- -----------------------------------------------------
-- Table `profilesdb`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `profilesdb`.`profile` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `town` VARCHAR(128) NOT NULL,
  `title` VARCHAR(128) NOT NULL,
  `experience` LONGTEXT,
  `formation` LONGTEXT,
  `technical_skills` LONGTEXT,
  `study` LONGTEXT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;