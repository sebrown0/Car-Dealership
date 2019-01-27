DELIMITER $$
/*
** Add an employee's sick period to the sickness table
** Duplicate sick periods are not allowed - constraint emp_year_start_end_unq
*/
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateEmployeeSickness` (
	IN emp_id INT, 			-- Emp's unique id
    IN start_date DATE, 	-- Start of sickness
    IN end_date DATE 		-- End of sickness
    )
BEGIN
	INSERT INTO 
		employee_sickness (`emp_sick_id`, `emp_sick_year`, `sick_start_date`, `sick_end_date`)
	VALUES
		(emp_id, (SELECT year(start_date)), start_date, end_date);

END;
DELIMITER;