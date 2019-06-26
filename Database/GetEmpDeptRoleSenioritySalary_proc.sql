CREATE PROCEDURE `GetEmpDeptRoleSenioritySalary`()
BEGIN
SELECT 
	concat(emp.first_name, ' ', emp.last_name) AS 'Employee Name', 
	dept.dept_name AS 'Department', 
	rle.role_name AS 'Role', 
	sen.seniority AS 'Seniority',
	emp.salary AS 'Salary'
FROM 
	employee emp
INNER JOIN
	department dept
ON
	dept.dept_id = emp.dept_id
INNER JOIN
	role_and_seniority ras
ON
	ras.ras_id = emp.ras_id
INNER JOIN
	seniority sen
ON
	sen.seniority_id = ras.seniority_id
INNER JOIN
	role rle
ON 
	rle.role_id = ras.role_id;
END;