/*
** Update the department table with a valid manager/emp id.
** i.e. the employee's seniority must = 'Manager' and the must belong to the department
*/
CREATE PROCEDURE `UpdateDepartmentManager`(IN new_manager_id INT, IN dept_name_in VARCHAR(45))
BEGIN
	DECLARE employee_seniority VARCHAR(25); -- Manager, Associate etc
    DECLARE department_id INT(11);			-- The department being updated
    DECLARE new_manager_dept_id INT(11);	-- The department id of the new manager.
        
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
			emp.emp_id = new_manager_id
	);
    
	SET department_id = (SELECT dept_id FROM department WHERE dept_name = dept_name_in);
    SET new_manager_dept_id = (SELECT dept_id FROM employee WHERE emp_id = new_manager_id);
	
    -- Update only if the employee is a Manager and is in the department.
    IF employee_seniority = 'Manager' AND department_id = new_manager_dept_id THEN
		UPDATE employee SET manager_id = new_manager_id WHERE dept_id = department_id;
	END IF;
END;