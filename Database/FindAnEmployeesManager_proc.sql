DELIMITER $$
/*
** Find an employee's manager from emp's id.
*/
CREATE PROCEDURE `FindAnEmployeesManager`(
	IN emp_id INT -- the unique id of the employee
	)
BEGIN
	SELECT 
		emp.emp_id AS 'Employee ID',
		CONCAT(emp.first_name, ' ', emp.last_name) AS 'Employee', 
		dept.dept_name AS 'Department', 
		CONCAT(emp_manager.first_name, ' ', emp_manager.last_name) AS 'Manager'
	FROM
		employees emp
	INNER JOIN
		departments dept
	ON 
		emp.dept_id =  dept.dept_id 
	INNER JOIN
		employees emp_manager
	ON 
		emp_manager.emp_id = dept.manager_id
	WHERE 
		emp.emp_id = emp_id; 
END;
DELIMITER;