CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDepartments`()
BEGIN
	SELECT 
		dept_id, dept_name  
	FROM 
		department
	WHERE
		dept_name != 'None'
    ORDER BY 
		dept_id;
END