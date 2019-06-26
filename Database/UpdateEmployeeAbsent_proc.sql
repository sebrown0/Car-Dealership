CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateEmployeeAbsent` 
	(IN emp_id INT, IN start_date DATE, IN end_date DATE, 
    IN num_days INT , IN reason VARCHAR(25))
BEGIN
	DECLARE abs_year YEAR(4);
	DECLARE existing_year YEAR(4);
    DECLARE abs_year_id INT;
    
	SET abs_year = (SELECT EXTRACT(YEAR FROM start_date));
    
    IF ((SELECT `Absent_Year`.`year` FROM Absent_Year 
			WHERE `Absent_Year`.`year` = abs_year AND `Absent_Year`.`fk_emp_id` = emp_id)) IS NULL THEN
            
		INSERT INTO `Absent_Year` (`fk_emp_id`, `year`) VALUE (emp_id, abs_year);
    END IF;
    COMMIT;
    
    SET abs_year_id = (SELECT y.absent_id FROM absent_year y WHERE y.fk_emp_id = emp_id AND y.year = abs_year);
    
	INSERT INTO `car_dealership`.`Employee_Absent` 
		(`absent_start_date`, `absent_end_date`, `num_days`, `reason`, `emp_absent_id`) 
	VALUES 
		(start_date, end_date, num_days, reason, abs_year_id);
END;