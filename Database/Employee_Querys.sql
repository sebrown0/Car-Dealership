select * from department;

select * from department_manager;

select * from employee order by dept_id;

select * from role;

select * from seniority;

select * from role_and_seniority ras 
inner join employee emp on emp.ras_id = ras.ras_id 
inner join seniority sen on sen.seniority_id = ras.seniority_id
inner join role rle on rle.role_id = ras.role_id;

UPDATE employee SET manager_id = NULL WHERE manager_id = 2;
UPDATE employee SET manager_id = 2 WHERE emp_id = 2;

call UpdateDepartmentManager (1, 'HR'); -- ( man_id,dept_id)
            
SELECT DISTINCT e.manager_id FROM employee e 
INNER JOIN department d ON d.dept_id = e.dept_id 
WHERE d.dept_name = 'HR';

call FindDepartmentManager(1);