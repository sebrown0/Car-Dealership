CREATE DEFINER=`root`@`localhost` PROCEDURE `NextCustomerId`()
BEGIN    
    SELECT MAX(customer_id) AS id FROM customers;
END;