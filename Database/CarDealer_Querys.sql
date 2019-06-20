/*
** Ad-hoc development/testing querys
*/

select * from department;

select * from department_manager;

select * from sales_dept;

select * from employee order by dept_id;

select sum(e.num_days) from employee_absent e join absent_year a on a.absent_id = e.absent_year_id
where a.emp_id = 1 and a.year = '2019' and e.reason = 'annual leave';

select * from employee_absent e join absent_year a on a.absent_id = e.absent_year_id
where a.emp_id = 1 and a.year = '2019';

select * from employee_absent order by absent_year_emp_id;

select * from absent_year order by emp_id;

select * from absent_year where emp_id =1 and year = 2019;

select * from Absent_Reason;

select * from role;

select * from seniority;

select * from role_and_seniority ras 
inner join employee emp on emp.ras_id = ras.ras_id 
inner join seniority sen on sen.seniority_id = ras.seniority_id
inner join role rle on rle.role_id = ras.role_id;

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

insert into absent_year (`emp_id`, `year`) values (1, '2020');
-- RESET END

select DATE(now());

-- call AddEmployee(args);
call FindAnEmployeesManager(3);
call GetEmpDeptRoleSenioritySalary();
call JoinEmployeesOnManagers();
call UpdateDepartmentManager (6,3); -- ( man_id,dept_id)

call GetModelDetails('Ford', 'Focus');

call NewCustomer("Tony", "el Tigro");

call GetStockList();

call MaxOrderNumber();

call UpdateEmployeeAbsent(10, '2019-06-19', '2019-06-20', 2, 'Sick'); 

call RollCall(1);

delete from employee_absent;

select sum(eabs.num_days) from employee_absent eabs 
join absent_year a on a.emp_id = eabs.emp_id where eabs.emp_id = 1 and a.year = '2019';

drop trigger Absent_Year_BEFORE_INSERT;

-- call RollCall(1, @emp_id); select @emp_id;
/*CREATE DEFINER = CURRENT_USER TRIGGER `car_dealership`.`Absent_Year_BEFORE_INSERT` 
BEFORE INSERT ON `Absent_Year` FOR EACH ROW
BEGIN
 SET NEW.year = YEAR(NOW());
END*/