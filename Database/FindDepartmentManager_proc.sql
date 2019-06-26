CREATE DEFINER=`root`@`localhost` PROCEDURE `FindDepartmentManager`(IN dept_id INT)
BEGIN
SELECT 
	d.dept_name AS `Department`, concat(e.first_name, " ", e.last_name) AS `Manager` 
FROM 
	employee e 
INNER JOIN 
	department d ON d.dept_id = e.dept_id 
WHERE 
	e.dept_id = dept_id AND e.emp_id = e.manager_id;
END