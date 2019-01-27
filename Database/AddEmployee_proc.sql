CREATE PROCEDURE `AddEmployee`(
	IN d_name VARCHAR(45),
    IN first_name VARCHAR(45),
    IN last_name VARCHAR(45),
    IN role_name VARCHAR(25),
    IN seniority VARCHAR(25),
    IN salary DECIMAL(10,2))
BEGIN
	DECLARE employee_id INT;
    DECLARE emp_role_id INT;
    DECLARE holiday_ent TINYINT(1);
  
	INSERT INTO 
		employees
			(`dept_id`, `first_name`, `last_name`)
	VALUES 
		((SELECT dept_id FROM departments WHERE dept_name = d_name ), first_name, last_name);
        
    COMMIT; -- so we can get the max id to insert into employees    
	SET employee_id = (SELECT MAX(emp_id) from employees);
    
    SET emp_role_id = (
		SELECT 
			rs.role_and_seniority_id
		FROM 
			role_and_seniority rs
        INNER JOIN 
			seniorities s
        ON 
			s.seniority_id = rs.seniority_id
		WHERE 
			s.seniority_id = rs.seniority_id
		AND 
			s.seniority = seniority 
        AND 
			rs.role_name = role_name
		);
    
	SET holiday_ent = 
		(SELECT 
			holiday_entitlement 
		FROM 
			role_and_seniority ras
		INNER JOIN
			salary_bands sb
		ON 
			ras.seniority_id = sb.seniority_id
		WHERE 
			ras.role_and_seniority_id = emp_role_id);
        
    INSERT INTO 
        human_resources (hr_emp_id, salary, role_and_seniority_id)
	VALUE
		(employee_id, salary, emp_role_id);
  
	INSERT INTO 
		employee_attendance (att_emp_id, att_year, annual_leave_remaining, sick_days, annual_leave)
	VALUES 
		( employee_id, (select year(now())) , holiday_ent, 0 ,holiday_ent);
	
END;