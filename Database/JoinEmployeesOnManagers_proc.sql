CREATE PROCEDURE `JoinEmployeesOnManagers`()
BEGIN
SELECT 
	emp.emp_id AS 'Employee ID',
    CONCAT(emp.first_name, ' ', emp.last_name) AS 'Employee', 
    dept.dept_name AS 'Department', 
    CONCAT(emp_manager.first_name, ' ', emp_manager.last_name) AS 'Manager'
FROM
    employee emp
INNER JOIN
    department dept
ON 
	emp.dept_id =  dept.dept_id 
LEFT JOIN
	employee emp_manager
ON 
	emp_manager.emp_id = dept.manager_id; 
END;