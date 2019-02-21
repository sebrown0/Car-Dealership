CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDeptIDs`()
BEGIN
	SELECT dept_id FROM departments;
END