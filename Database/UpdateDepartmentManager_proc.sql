/*
** Update the department table with a valid manager/emp id.
** i.e. the employee's seniority must = 'Manager'
*/
CREATE PROCEDURE `UpdateDepartmentManager`(IN employee_id INT, IN dept_name_in VARCHAR(45))
BEGIN
	DECLARE employee_seniority VARCHAR(25);
    DECLARE existing_dept_name VARCHAR(45);
    
    SET employee_seniority = ( 
		SELECT  
			sen.seniority
		FROM 
			employee emp
		INNER JOIN 
			role_and_seniority ras
		ON 
			ras.ras_id = emp.ras_id
		INNER JOIN         
			seniority sen
		ON
			sen.seniority_id = ras.seniority_id 
		WHERE 
			emp.emp_id = employee_id
	);

	IF employee_seniority = 'Manager' THEN
		
        SET existing_dept_name = 
			(SELECT dept_name FROM Department_Manager WHERE Department_Manager.dept_name = dept_name_in);
        
        IF existing_dept_name IS NULL THEN
			INSERT INTO 
				Department_Manager (`manager_id`, `dept_id`, `dept_name`) 
			VALUES 
				(employee_id, (SELECT dept_id FROM department d WHERE d.dept_name = dept_name_in), dept_name_in);
        ELSE        
			UPDATE 
				Department_Manager
			SET	
				manager_id = employee_id
			WHERE 
				Department_Manager.dept_name = dept_name_in;
		END IF;
	END IF;
END;