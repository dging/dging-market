DELIMITER //

CREATE PROCEDURE reset_auto_increment()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE tbl_name VARCHAR(255);
    DECLARE cur CURSOR FOR
SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'dgingmarket_test_db'
  AND table_type = 'BASE TABLE';

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    SET FOREIGN_KEY_CHECKS = 0;

OPEN cur;

read_loop: LOOP
        FETCH cur INTO tbl_name;
        IF done THEN
            LEAVE read_loop;
END IF;

        SET @stmt = CONCAT('ALTER TABLE ', tbl_name, ' AUTO_INCREMENT = 1');
PREPARE stmt FROM @stmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END LOOP;

CLOSE cur;

SET FOREIGN_KEY_CHECKS = 1;
END //

DELIMITER ;