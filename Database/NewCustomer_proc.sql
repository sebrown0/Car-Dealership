CREATE DEFINER=`root`@`localhost` PROCEDURE `NewCustomer`(
    IN first_name VARCHAR(45),
    IN last_name VARCHAR(45))
BEGIN
	INSERT INTO 
		customers (first_name, last_name)
	VALUE
		(first_name, last_name);
        
	SELECT MAX(customer_id) AS id from customers;
            
END;