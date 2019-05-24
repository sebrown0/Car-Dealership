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

select * from customers;

select * from model;

select * from model_attributes;

select * from model_enhancements;

select * from stock_list;

select * from stock_status;

select * from stock_updates;

select * from order_list;

-- RESET START

delete from order_list;
delete from stock_list;
delete from model_attributes;
delete from model_enhancements;
delete from model;
delete from stock_updates;
delete from customers;

INSERT INTO `stock_updates`	(`update_id`, `file_name`) VALUES (0,  'car_stock_0.json');		

INSERT INTO `Customers` (`first_name`,`last_name`) VALUES ('Steve','Brown');    
-- RESET END


select DATE(now());

-- call AddEmployee(args);
call FindAnEmployeesManager(3);
call GetEmpDeptRoleSenioritySalary();
call JoinEmployeesOnManagers();
call UpdateDepartmentManager (6,3); -- ( man_id,dept_id)
call BookHoliday(1,'2019-01-01','2019-01-04');

call BookHoliday(12,'2019-02-17','2019-02-22');
call BookHoliday(4,'2019-02-07','2019-04-14');

call GetModelDetails('Ford', 'Focus');

call NewCustomer("Tony", "el Tigro");

call GetStockList();

call MaxOrderNumber();

call RollCall(5);

-- DELETE from employees WHERE employees.emp_id = 6;

-- SELECT * from employee_holiday;
-- select date_format(curdate(), '%Y');
/*
INSERT INTO 
	employee_holiday (`emp_hol_rec_id`, `hol_rec_year`, `hol_start_date`, `hol_end_date`)
VALUES
	('5',  '2019', '2019-02-15', '2019-02-28');
*/