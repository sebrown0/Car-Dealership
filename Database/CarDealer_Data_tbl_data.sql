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
    
INSERT INTO `Customer` 
	(`first_name`,`last_name`)
VALUES
	('Steve','Brown');

-- Create department
INSERT INTO `department` 
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
    
-- Add seniority levels for each role
INSERT INTO `seniority` 
	(`seniority`, `description`, `salary_min`, `salary_max`, `Holiday_entitlement`) 
VALUES 
	('Junior Associate','', 10000, 15000, 25),
    ('Associate','', 15001, 22000, 25),
    ('Senior Associate','', 22001, 40000, 25),
    ('Manager','', 31000, 62000, 25),
    ('Director','', 40000, 75000, 25),
    ('Apprentice','', 8000, 10000, 20),
    ('Skilled','', 25000, 40000, 20),
    ('Unskilled','', 10000, 20000, 20),
    ('CEO','', 100000, 150000, 25);

-- Add roles
-- role_id - auto num unique id for role
-- role_name - name of the role i.e. accountant
INSERT INTO `role` 
	(`role_name`, `description`, `is_commision_payable`) 
VALUES 
	('HR','human resources',0),
	('Salesperson', '',1), 
    ('Receptionist','',0),
    ('Seceratary','',0),
    ('Developer','',0), 
    ('Accountant','',0),
    ('Mechanic','',0),
    ('Valet','',0),
    ('PA','',0),
    ('Storeperson','',0),
    ('Clerk','',0),
    ('CEO','',1); 
    
-- Add applicable seniority level(s) for each role
-- role_id - the role
-- seniority_id - the seniority level
INSERT INTO `Role_And_Seniority` 
	(`role_id`, `seniority_id`) 
VALUES 
	(1, 1), -- HR employee Junior Associate
    (1, 2), -- HR employee Associate
    (1, 3), -- HR employee Senior Associate
    (1, 4), -- HR employee Manager
    (1, 5), -- HR employee Director
    
    (2, 1), -- Sales Junior Associate
    (2, 2), -- Sales Associate
    (2, 3), -- Sales Senior Associate
    (2, 4), -- Sales Manager
    (2, 5), -- Sales Director
    
    (3, 1), -- Receptionist Junior Associate
    (3, 2), -- Receptionist Associate
    (3, 3), -- Receptionist Senior Associate
    
    (4, 1), -- Seceratary Junior Associate
    (4, 2), -- Seceratary Associate
    (4, 3), -- Seceratary Junior Associate
    
	(5, 1), -- Developer Junior Associate
    (5, 2), -- Developer Associate
    (5, 3), -- Developer Senior Associate
    (5, 4), -- Developer Manager
    
    (6, 1), -- Accountant Junior Associate
    (6, 2), -- Accountant Associate
    (6, 3), -- Accountant Senior Associate
    (6, 4), -- Accountant Manager
    (6, 5), -- Director
    
    (7, 4), -- Mechanic Manager
    (7, 6), -- Mechanic Apprentice
    (7, 7), -- Mechanic Skilled
    
    (8, 8), -- Valet Unskilled
    
    (9, 2), -- PA Associate
    (9, 3), -- PA Senior Associate
    
    (10, 1), -- Storeperson Junior Associate
    (10, 2), -- Storeperson Associate
    (10, 3), -- Storeperson Senior Associate
    (10, 4), -- Storeperson Manager
    
    (11, 1), 		-- Clerk Junior Associate
    (11, 2), 		-- Clerk Associate
    (11, 3), 		-- Clerk Senior Associate
    (11, 4), 		-- Clerk Manager
    
    (12, 9); -- CEO
    
    
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
-- SSN
-- DOB
call AddEmployee('HR','Mary','Queen Of Scots','HR', 'Associate', 20000, '1821-QS-1969', '1969-06-29',(select DATE(now()))); 	
call AddEmployee('HR', 'Sid', 'James','HR', 'Manager', 32000, '5434-SJ-1957', '1957-12-18', (select DATE(now()))); 
call AddEmployee('Accounts', 'Marge', 'Simpson','Seceratary', 'Associate', 21222, '7910-MS-1980', '1980-08-27', (select DATE(now())));  			
call AddEmployee('Accounts', 'Ben', 'Stokes','Accountant', 'Manager', 50000, '7613-BS-1991', '1991-08-16', (select DATE(now())));
call AddEmployee('IT', 'Sally', 'Field','Developer', 'Senior Associate', 26000, '1113-SF-1972', '1972-04-05', (select DATE(now())));
call AddEmployee('IT', 'Lisa', 'Simpson','Developer', 'Manager', 39000, '7600-LS-1997', '1997-06-16', (select DATE(now())));  --
call AddEmployee('Garage Services', 'Imran', 'Tahir','Mechanic', 'Skilled', 28999, '9715-IT-1978', '1978-11-01', (select DATE(now())));
call AddEmployee('Garage Services', 'Bat', 'Man','Mechanic','Skilled', 29000, '6666-BM-1964', '1964-07-09', (select DATE(now())));
call AddEmployee('Garage Services', 'The', 'Rock','Valet', 'Unskilled', 11000, '9671-TR-1992', '1992-12-12', (select DATE(now())));    
call AddEmployee('Garage Services', 'Bart', 'Simpson','Mechanic', 'Manager', 31000, '1213-BS-1996', '1996-10-22', (select DATE(now())));    
call AddEmployee('Sales', 'Clint', 'Eastwood','Salesperson', 'Senior Associate', 24000, '1213-CE-1956', '1956-10-22', (select DATE(now())));
call AddEmployee('Sales', 'Maggie', 'Smith','Salesperson', 'Manager', 42343, '1213-MS-1951', '1951-11-12', (select DATE(now())));
call AddEmployee('Sales', 'Homer', 'Simpson','Receptionist', 'Associate', 17999, '7600-HS-1977', '1977-04-11', (select DATE(now())));   
call AddEmployee('None', 'Queen', 'Elizabeth','CEO', 'CEO', 120000, '1033-ER-1972', '1922-05-27', (select DATE(now())));
call AddEmployee('None', 'Cleo', 'Rocos','PA', 'Associate', 19000, '1199-CR-1990', '1990-03-05', (select DATE(now())));
call AddEmployee('Stock', 'John', 'Wayne','Storeperson', 'Junior Associate', 14000, '8912-JW-1940', '1940-03-25', (select DATE(now())));
call AddEmployee('Stock', 'Tiger', 'Woods','Storeperson', 'Manager', 32300, '7112-TW-1990', '1990-04-21', (select DATE(now())));
call AddEmployee('Order', 'Queen', 'Victoria','Clerk', 'Manager', 31500, '1222-QV-1900', '1900-09-05', (select DATE(now())));   
call AddEmployee('Order', 'Liz', 'Hurley','Clerk', 'Associate', 17500, '1111-LH-1987', '1987-01-08', (select DATE(now())));   
-- Add some managers to department

INSERT INTO `Absent_Reason`
	(`reason_type_id`, `reason`)
VALUES
	(1, 'Annual Leave'),
    (2, 'Sick'),
    (3, 'Sabatical'),
    (4, 'Compassionate Leave');
    
call UpdateDepartmentManager (2, 'HR'); 
call UpdateDepartmentManager (6, 'IT'); 
call UpdateDepartmentManager (4, 'Accounts'); 
call UpdateDepartmentManager (10, 'Garage Services');
call UpdateDepartmentManager (12, 'Sales'); 
call UpdateDepartmentManager (17, 'Stock');
call UpdateDepartmentManager (18, 'Order');

INSERT INTO `absent_year`
	(`emp_id`, `year`)
VALUES
	(1, '2019'),
    (1, '2020'),
    (2, '2019'),
    (3, '2019');
    
call UpdateEmployeeAbsent(1, (select DATE(now())), (select DATE(now())), 1, 'Annual Leave');
call UpdateEmployeeAbsent(2, '2020-06-21', '2020-06-24', 3, 'Sick');
call UpdateEmployeeAbsent(1, '2019-06-21', '2019-06-22', 1, 'Sick'); 