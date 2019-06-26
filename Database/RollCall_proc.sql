CREATE DEFINER=`root`@`localhost` PROCEDURE `RollCall`(IN department_id INT)
BEGIN
SELECT 
	emp.* -- emp.first_name, emp.last_name, dept.dept_name, sen.seniority, r.role_name
FROM 
	employee emp
JOIN 
	department dept
ON
	emp.dept_id = dept.dept_id
JOIN 
	role_and_seniority ras
ON
	ras.ras_id = emp.ras_id
JOIN
	seniority sen
ON 
	ras.seniority_id = sen.seniority_id
JOIN
	role r
ON
	r.role_id = ras.role_id
WHERE
	emp.dept_id = department_id
AND
	emp.emp_id NOT IN (
		SELECT 
			emp.emp_id
		FROM
			employee emp
		JOIN
			absent_year abs_yr
		ON
			abs_yr.fk_emp_id = emp.emp_id AND abs_yr.year = (SELECT year(now()))
		JOIN
			employee_absent emp_abs
		ON
			emp_abs.emp_absent_id = abs_yr.fk_emp_id
		WHERE		
			(SELECT curdate()) >= emp_abs.absent_start_date AND (SELECT curdate()) <= emp_abs.absent_end_date 
		);
END