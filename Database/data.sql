DELIMITER //
DROP PROCEDURE IF EXISTS `profilesdb`.`seqInsertProfile`//

CREATE PROCEDURE `profilesdb`.seqInsertProfile (IN i INT) 
BEGIN   
DECLARE count INT DEFAULT 1;
WHILE COUNT <= i DO
INSERT INTO `profilesdb`.`profile` (name, firstname, town, title, experience, formation, technical_skills, study)
VALUES (CONCAT('John',COUNT), CONCAT('Smith',COUNT), CONCAT('town',COUNT), CONCAT('title',COUNT), CONCAT('experience',COUNT), CONCAT('formation',COUNT), CONCAT('technical_skills',COUNT), CONCAT('study',COUNT));
SET count = count + 1;   
END WHILE; 
END//

DELIMITER ;

call `profilesdb`.seqInsertProfile (100);