/*
** Update the department table with a valid manager/emp id.
** i.e. the employee's seniority must = 'Manager'
*/
CREATE PROCEDURE `UpdateDepartmentManager`(IN employee_id INT, IN department_id INT)
BEGIN
	DECLARE employee_seniority VARCHAR(25);
    SET employee_seniority = ( 
		SELECT  
			sen.seniority
		FROM 
			human_resources hr
            
		INNER JOIN 
			role_and_seniority ras
		ON
			hr.role_and_seniority_id = ras.role_and_seniority_id
		INNER JOIN
			seniorities sen
		ON
			ras.seniority_id = sen.seniority_id
		WHERE 
			hr.hr_emp_id = employee_id
		);
	
		IF employee_seniority = 'Manager' THEN
			UPDATE 
				departments
			SET	
				manager_id = employee_id
			WHERE 
				departments.dept_id = department_id;
		END IF;
END;