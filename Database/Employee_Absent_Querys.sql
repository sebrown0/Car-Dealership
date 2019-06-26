/*
** Development and testing queries for HR.
*/
-- RESET START
DELETE FROM employee_absent;
DELETE FROM absent_year;

INSERT INTO `absent_year`
	(`fk_emp_id`, `year`)
VALUES
	(1, '2019'), (1, '2020'), (2, '2019'), (3, '2019');
    
call UpdateEmployeeAbsent(1, (select DATE(now())), (select DATE(now())), 1, 'Annual Leave');
call UpdateEmployeeAbsent(2, '2020-06-21', '2020-06-24', 3, 'Sick');

-- RESET END

select sum(e.num_days) from employee_absent e join absent_year a on a.absent_id = e.emp_absent_id
where a.fk_emp_id = 7 and a.year = '2019' and e.reason = 'Sick';

select * from employee_absent e join absent_year a on a.absent_id = e.emp_absent_id
join employee emp on emp.emp_id = a.fk_emp_id
where a.fk_emp_id  IN(1,2,5,7) and a.year = '2019';

select * from absent_year order by fk_emp_id;

select * from employee_absent order by emp_absent_id;

select * from Absent_Reason;