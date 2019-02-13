CREATE DEFINER=`root`@`localhost` PROCEDURE `MaxOrderNumber`()
BEGIN
	SELECT MAX(order_id) AS id from order_list;
END;