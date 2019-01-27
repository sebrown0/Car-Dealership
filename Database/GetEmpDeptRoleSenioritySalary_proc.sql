CREATE PROCEDURE `GetEmpDeptRoleSenioritySalary`()
BEGIN
	SELECT 
		concat(emp.first_name, emp.last_name) AS 'Employee Name', 
		dept.dept_name AS 'Department', 
		ras.role_name AS 'Role', 
		sen.seniority AS 'Seniority',
		hr.salary AS 'Salary'
	FROM 
		human_resources hr
	INNER JOIN
		employees emp
	ON
		emp.emp_id = hr.hr_emp_id
	INNER JOIN
		departments dept
	ON
		dept.dept_id = emp.dept_id
	INNER JOIN
		role_and_seniority ras
	ON
		ras.role_and_seniority_id = hr.role_and_seniority_id
	INNER JOIN
		seniorities sen
	ON
		sen.seniority_id = ras.seniority_id;
END;