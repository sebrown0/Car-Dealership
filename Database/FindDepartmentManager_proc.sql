CREATE DEFINER=`root`@`localhost` PROCEDURE `FindDepartmentManager`(IN dept_id INT)
BEGIN
SELECT 
	hr_emp_id, first_name, last_name, 
    emp.dept_id, 
    sen.seniority, sen.seniority_id, 
    rle.role_id, rle.role_name
FROM 
	human_resources hr
JOIN
	employees emp
ON
	emp.emp_id = hr.hr_emp_id
JOIN 
	role_and_seniority ras
ON
	hr.role_and_seniority_id = ras.role_and_seniority_id
JOIN
	seniorities sen
ON 
	ras.seniority_id = sen.seniority_id
JOIN 
	roles rle
ON
	ras.role_id = rle.role_id
WHERE
	emp.dept_id = dept_id AND sen.seniority_id = 4; 
END