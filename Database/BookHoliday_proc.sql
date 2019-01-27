DELIMITER $$
/*
** Simplified routine for booking annual leave.
** DOESN'T take into account if a holiday request includes a weekend so the employee will have
** a weekend deducted from their entitlement!
** Deducts the amount of holiday days asked for from existing entitlement.
** If there are enough days left and adds the dates to the "calander".
** Duplicate holidays are not allowed - constraint emp_year_start_end_unq
*/
CREATE DEFINER=`root`@`localhost` PROCEDURE `BookHoliday` (IN emp_id INT, IN start_date DATE, IN end_date DATE )
BEGIN
	DECLARE holiday_remaining TINYINT;
    DECLARE days_in_holiday_request INT;
    
    IF (( SELECT DATEDIFF ( start_date, (SELECT DATE( now() )) )) > 0 ) THEN
		-- We're not trying to add dates from the past so carry on
		SET holiday_remaining = (
			SELECT 
				annual_leave_remaining
			FROM
				employee_attendance
			WHERE
				att_emp_id = emp_id
		);
			
		SET days_in_holiday_request = (SELECT DATEDIFF (end_date, start_date));
		
		IF holiday_remaining >= days_in_holiday_request THEN 
			-- update employee's entitlement & insert hol dates into "calender"
			INSERT INTO 
				employee_holiday (`emp_hol_rec_id`, `hol_rec_year`, `hol_start_date`, `hol_end_date`)
			VALUES
				(emp_id, (SELECT year(start_date)), start_date, end_date);
				
			UPDATE 
				employee_attendance
			SET 
				annual_leave_remaining = (holiday_remaining - days_in_holiday_request)
			WHERE
				att_emp_id = emp_id;
		ELSE
			SELECT 'Not enough annual leave remaining';
		END IF;
	ELSE	
		SELECT 'Cannot add historic date';
	END IF;
END;
DELIMITER;