/*
** Initial data for the schema.
*/
/**************** CAR DATA ****************/
INSERT INTO `manufacturer`
	(`manufacturer_name`)
VALUES
	('Ford'),
    ('Volkswagen');
    
INSERT INTO `model`
	(`model_vin`,`manufacturer_id`, `model_name`, `retail_price`, `date_of_manufacture`)
VALUES
	('FD95219PKV', 1, 'Focus', 22000, '2019-01-01');			-- Ford
    
INSERT INTO `model_attributes`
	(`model_vin`, `colour`, `transmission`, `horsepower`)
VALUES
	('FD95219PKV', 'Red', 'Auto', 2000);	
    
INSERT INTO `model_enhancements`
	(`model_vin`, `sunroof`, `alloy_wheels`, `ac`)
VALUES
	('FD95219PKV', 0, 1, 1);	

INSERT INTO `stock_status`
	(`status`)
VALUES
	('Order'),					-- New order
	('Awaiting Preparation'),	-- Car has just arrived so can't be viewed or sold
    ('Forecourt'),				-- Can be sold
    ('Test'),					-- On a test drive
    ('Sold'),					-- Sold awaiting delivery
    ('Delivered');				-- Car has been delivered to customer

INSERT INTO `stock_updates`
	(`update_id`, `file_name`)
VALUES
	(0,  'car_stock_0.json');		
    
INSERT INTO `Customers` 
	(`first_name`,`last_name`)
VALUES
	('Steve','Brown');

-- Create departments
-- SET foreign_key_checks = 0;
INSERT INTO `departments` 
	(`dept_name`, `description`) 
VALUES 
	('HR','Human Resource'),
	('Sales','Sales'),
	('IT','Information Technology'),
    ('Accounts','Responsible for pay cheques, invoicing etc'),
	('Garage Services','Resposible for car servicing, repairs etc'),
    ('Stock','Resposible for updating stock'),
    ('Order','Resposible for ordering stock'),
    ('None','An employee can belong to no department i.e. the CEO');
-- SET foreign_key_checks = 1;   

-- Add roles
-- role_id - auto num unique id for role
-- role_name - name of the role i.e. accountant
INSERT INTO `Roles` 
	(`role_name`, `description`) 
VALUES 
	('HR','human resources'),
	('Salesperson', ''), 
    ('Receptionist',''),
    ('Seceratary',''),
    ('Developer',''), 
    ('Accountant',''),
    ('Mechanic',''),
    ('Valet',''),
    ('PA',''),
    ('Storeperson',''),
    ('Clerk',''),
    ('CEO',''); 
    
-- Add seniority levels for each role
-- seniority_id - auto num
-- seniority - actual seniority 
-- description - description of duties etc
INSERT INTO `Seniorities` 
	(`seniority`, `description`) 
VALUES 
	('Junior Associate',''),
    ('Associate',''),
    ('Senior Associate',''),
    ('Manager',''),
    ('Director',''),
    ('Apprentice',''),
    ('Skilled',''),
    ('Unskilled',''),
    ('CEO','');

-- Add applicable seniority level(s) for each role
-- role_id - the role
-- role_id - name of the role
-- seniority_id - the seniority level
INSERT INTO `Role_And_Seniority` 
	(`role_id`, `role_name`, `seniority_id`) 
VALUES 
	(1, 'HR', 1), -- HR employee Junior Associate
    (1, 'HR', 2), -- HR employee Associate
    (1, 'HR', 3), -- HR employee Senior Associate
    (1, 'HR', 4), -- HR employee Manager
    (1, 'HR', 5), -- HR employee Director
    
    (2, 'Salesperson', 1), -- Sales Junior Associate
    (2, 'Salesperson', 2), -- Sales Associate
    (2, 'Salesperson', 3), -- Sales Senior Associate
    (2, 'Salesperson', 4), -- Sales Manager
    (2, 'Salesperson', 5), -- Sales Director
    
    (3, 'Receptionist', 1), -- Receptionist Junior Associate
    (3, 'Receptionist', 2), -- Receptionist Associate
    (3, 'Receptionist', 3), -- Receptionist Senior Associate
    
    (4, 'Seceratary', 1), -- Seceratary Junior Associate
    (4, 'Seceratary', 2), -- Seceratary Associate
    (4, 'Seceratary', 3), -- Seceratary Junior Associate
    
	(5, 'Developer', 1), -- Developer Junior Associate
    (5, 'Developer', 2), -- Developer Associate
    (5, 'Developer', 3), -- Developer Senior Associate
    (5, 'Developer', 4), -- Developer Manager
    
    (6, 'Accountant', 1), -- Accountant Junior Associate
    (6, 'Accountant', 2), -- Accountant Associate
    (6, 'Accountant', 3), -- Accountant Senior Associate
    (6, 'Accountant', 4), -- Accountant Manager
    (6, 'Accountant', 5), -- Director
    
    (7, 'Mechanic', 4), -- Mechanic Manager
    (7, 'Mechanic', 6), -- Mechanic Apprentice
    (7, 'Mechanic', 7), -- Mechanic Skilled
    
    (8, 'Valet', 8), -- Valet Unskilled
    
    (9, 'PA', 2), -- PA Associate
    (9, 'PA', 3), -- PA Senior Associate
    
    (10, 'Storeperson', 1), -- Storeperson Junior Associate
    (10, 'Storeperson', 2), -- Storeperson Associate
    (10, 'Storeperson', 3), -- Storeperson Senior Associate
    (10, 'Storeperson', 4), -- Storeperson Manager
    
    (11, 'Clerk', 1), 		-- Clerk Junior Associate
    (11, 'Clerk', 2), 		-- Clerk Associate
    (11, 'Clerk', 3), 		-- Clerk Senior Associate
    (11, 'Clerk', 4), 		-- Clerk Manager
    
    (12, 'CEO', 9); -- CEO
    
-- Add salary band for each level of seniority
-- salary_band_id - auto num
-- seniority_id -- the role for which the band applies
-- salary_min - min salary for band
-- salary_max - max salary for band
-- Holiday_entitlement - holiday entitlement for this band
-- commision_payable - is commision payable
INSERT INTO `Salary_Bands` 
	(`seniority_id`, `salary_min`, `salary_max`, `Holiday_entitlement`, `commision_payable`) 
VALUES  
	(1, 10000, 15000, 25, 0), -- Junior Associate
    (2, 15001, 22000, 25, 0), -- Associate
    (3, 22001, 40000, 25, 0), -- Senior Associate
    (4, 31000, 62000, 25, 0), -- Manager
    (5, 40000, 75000, 25, 1), -- Director
    (6, 8000, 10000, 20, 1), -- Apprentice
    (7, 25000, 40000, 20, 1), -- Skilled
    (8, 10000, 20000, 20, 0), -- Unskilled
    (9, 50001, 130000, 20, 0); -- CEO
    
-- Initial values for the sales department.
-- dept_id is set to defualt 2
INSERT INTO `sales_dept`
	(`sales_target_year`, `actual_sales_year`, `actual_sales_month`)
VALUES
	(400000,0,0);  
    
-- Add (INSERT) to the employees table 
-- dept name
-- first name
-- last name
-- role name
-- seniority
-- salary
call AddEmployee('HR','Mary','Queen Of Scots','HR', 'Associate', 20000); 	
call AddEmployee('HR', 'Sid', 'James','HR', 'Manager', 30000); --
call AddEmployee('Accounts', 'Marge', 'Simpson','Seceratary', 'Associate', 22222);  			
call AddEmployee('Accounts', 'Ben', 'Stokes','Accountant', 'Manager', 50000);
call AddEmployee('IT', 'Sally', 'Field','Developer', 'Senior Associate', 26000);
call AddEmployee('IT', 'Lisa', 'Simpson','Developer', 'Manager', 30000);  --
call AddEmployee('Garage Services', 'Imran', 'Tahir','Mechanic', 'Skilled', 28999);
call AddEmployee('Garage Services', 'Bat', 'Man','Mechanic','Skilled', 29000);
call AddEmployee('Garage Services', 'The', 'Rock','Valet', 'Unskilled', 11000);    
call AddEmployee('Garage Services', 'Bart', 'Simpson','Mechanic', 'Manager', 31000);    
call AddEmployee('Sales', 'Clint', 'Eastwood','Salesperson', 'Senior Associate', 24000);
call AddEmployee('Sales', 'Maggie', 'Smith','Salesperson', 'Manager', 42343);
call AddEmployee('Sales', 'Homer', 'Simpson','Receptionist', 'Associate', 17999);   
call AddEmployee('None', 'Queen', 'Elizabeth','CEO', 'CEO', 80000);
call AddEmployee('None', 'Cleo', 'Rocos','PA', 'Associate', 19000);
call AddEmployee('Stock', 'John', 'Wayne','Storeperson', 'Junior Associate', 14000);
call AddEmployee('Stock', 'Tiger', 'Woods','Storeperson', 'Manager', 29000);
call AddEmployee('Order', 'Queen', 'Victoria','Clerk', 'Manager', 27500);   
call AddEmployee('Order', 'Liz', 'Hurley','Clerk', 'Associate', 17500);   
-- Add some managers to departments

call UpdateDepartmentManager (2,1); -- ( man_id,dept_id)
call UpdateDepartmentManager (12,2); -- ( man_id,dept_id)
call UpdateDepartmentManager (6,3); -- ( man_id,dept_id)
call UpdateDepartmentManager (4,4); -- ( man_id,dept_id)
call UpdateDepartmentManager (10,5); -- ( man_id,dept_id)
call UpdateDepartmentManager (17,6); -- ( man_id,dept_id)
call UpdateDepartmentManager (18,7); -- ( man_id,dept_id)

-- SET foreign_key_checks = 1;
INSERT INTO `Employee_Data` 
    (`data_emp_id`, `social_security_number`, `dob`, `hire_date`, `emergency_contact`, `gender`) 
VALUES 
	(1, '1821-QS-1969', '1969-06-29',(select DATE(now())), '', 'Female'),
    (2, '5434-SJ-1957', '1957-12-18', (select DATE(now())),'Hatie Jackes', 'Male'),
    (3, '7910-MS-1980', '1980-08-27', (select DATE(now())),'Snowballs', 'Female'),
	(4, '7613-BS-1991', '1991-08-16', (select DATE(now())),'', 'Male'),
    (5, '1113-SF-1972', '1972-04-05', (select DATE(now())),'Burt Reynolds','Male'),
    (6, '7600-LS-1997', '1997-06-16', (select DATE(now())),'Marge Simpson', 'Female'),
    (7, '9715-IT-1978', '1978-11-01', (select DATE(now())),'Mrs Tahir', 'Male'),
    (8, '6666-SF-1964', '1964-07-09', (select DATE(now())),'Robin', 'Male'),
    (9, '9671-TR-1992', '1992-12-12', (select DATE(now())),'', 'Male'),
	(10, '1937-BS-1989', '1989-08-02', (select DATE(now())), '', 'Male'),
	(11, '1213-CE-1956', '1956-10-22', (select DATE(now())),'', 'Male'),
	(13, '7600-HS-1977', '1977-04-11', (select DATE(now())),'Marge Simpson','Male'),
	(14, '1033-ER-1972', '1922-05-27', (select DATE(now())),'Prince Philip', 'Female'),
    (15, '1199-CR-1990', '1990-03-05', (select DATE(now())),'', 'Female'),
    (16, '8912-JW-1940', '1940-03-25', (select DATE(now())),'', 'Male'),
    (17, '7112-TW-1990', '1990-04-21', (select DATE(now())),'', 'Male'),
    (18, '1222-QV-1900', '1900-09-05', (select DATE(now())),'', 'Female'),
    (19, '1111-LH-1987', '1987-01-08', (select DATE(now())),'', 'Female');
    
-- Add some holidays for a few employees WON'T WORK IF HISTORIC DATE
-- USE INSERTS FOR HISTORIC DATES AS THE SP CHECK THIS.
/*call BookHoliday(2,'2019-05-01','2019-05-04');
call BookHoliday(2,'2019-06-01','2019-06-14');
call BookHoliday(6,'2019-03-11','2019-03-29');
call BookHoliday(9,'2019-11-01','2019-11-09');
call BookHoliday(11,'2019-02-18','2019-03-12');
*/