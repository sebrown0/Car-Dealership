
select * from departments;
select * from sales_dept;

select * from employees;
select * from employee_data;
select * from employee_attendance;
select * from employee_holiday;

select * from human_resources;

select * from roles;
select * from role_and_seniority;
select * from salary_bands;
select * from seniorities;

select DATE(now());

-- call AddEmployee(args);
call FindAnEmployeesManager(3);
call GetEmpDeptRoleSenioritySalary();
call JoinEmployeesOnManagers();
call UpdateDepartmentManager (6,3); -- ( man_id,dept_id)
call BookHoliday(1,'2019-01-01','2019-01-04');

call BookHoliday_e(4,'2019-11-01','2019-11-04');
call BookHoliday_e(4,'2019-02-07','2019-04-14');
select * from employee_holiday;


DELETE from employees WHERE employees.emp_id = 6;

