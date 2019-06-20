/*
*	Add an employee and update the HR
*	and Employee Renumeration tables.
*/
CREATE PROCEDURE `AddEmployee`(
	IN d_name VARCHAR(45),
    IN first_name VARCHAR(45),
    IN last_name VARCHAR(45),
    IN r_name VARCHAR(25),
    IN snrty VARCHAR(25),
    IN salary DECIMAL(10,2),
    IN ssn VARCHAR(45),
    IN dob DATE,
    IN hire_date DATE)
BEGIN
	DECLARE employee_id INT;
    DECLARE renum_id INT;
    DECLARE ras_id INT;
    DECLARE holiday_ent TINYINT(1);
        
    -- Get RAS ID for the given seniority and role    
    SET ras_id = (
		SELECT 
			ras.ras_id
		FROM 
			role_and_seniority ras
		INNER JOIN 
			seniority s
		ON 
			s.seniority_id = ras.seniority_id
		INNER JOIN
			role r
		ON 
			r.role_id = ras.role_id
		WHERE 
			s.seniority = snrty
		AND 
			r.role_name = r_name
		);
        
	-- get anuual leave for a RAS.
	SET holiday_ent = (
	SELECT 
			sen.holiday_entitlement 
		FROM 
			role_and_seniority ras
		INNER JOIN
			seniority sen
		ON 
			ras.seniority_id = sen.seniority_id
		WHERE 
			ras.ras_id = ras_id
	);
    
	INSERT INTO employee
		(`dept_id`, `ras_id`, `first_name`, `last_name`, `salary`, 
        `annual_leave`, `ssn`, `dob`, `hire_date`)
	VALUES 
		((SELECT dept_id FROM department WHERE dept_name = d_name ), 
        ras_id, first_name, last_name, salary, holiday_ent, ssn, dob, hire_date);
END;