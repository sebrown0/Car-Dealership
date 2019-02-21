select * from human_resources hr1
where hr1.hr_emp_id NOT IN (
SELECT 
	hr_emp_id AS emp_id
FROM 
	human_resources hr
JOIN
	employees emp
ON
	emp.emp_id = hr.hr_emp_id
JOIN
	employee_attendance att
ON
	att.att_emp_id = emp.emp_id
JOIN
	employee_holiday hol
ON
	hol.emp_hol_rec_id = att.att_emp_id
WHERE
	hol.hol_rec_year = (select date_format(curdate(), '%Y')) -- Only interested in current year
AND
	(SELECT curdate()) > hol.hol_start_date AND (SELECT curdate()) < hol.hol_end_date -- Only interested in those on holiday
)
;