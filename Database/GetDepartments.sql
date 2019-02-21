CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDepartments`()
BEGIN
	SELECT dept_id, dept_name  FROM departments ORDER BY dept_id;
END