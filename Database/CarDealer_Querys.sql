/*
** Ad-hoc development/testing querys
*/
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

select * from manufacturer;

select * from model;
select * from model_attributes;
select * from model_enhancements;
select * from stock_list;
select * from stock_status;
select * from order_list;
select * from stock_updates;


delete from model;
delete from model_attributes;
delete from model_enhancements;
delete from order_list;
delete from stock_list;

delete from stock_updates;

INSERT INTO `stock_updates`
	(`update_id`, `file_name`)
VALUES
	(0,  'car_stock_0.json');		

select DATE(now());

-- call AddEmployee(args);
call FindAnEmployeesManager(3);
call GetEmpDeptRoleSenioritySalary();
call JoinEmployeesOnManagers();
call UpdateDepartmentManager (6,3); -- ( man_id,dept_id)
call BookHoliday(1,'2019-01-01','2019-01-04');

call BookHoliday(4,'2019-11-01','2019-11-04');
call BookHoliday(4,'2019-02-07','2019-04-14');

call GetModelDetails('Ford', 'Focus');
-- DELETE from employees WHERE employees.emp_id = 6;

