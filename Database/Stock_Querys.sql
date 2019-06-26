-- RESET STOCK START
delete from stock_file;
delete from stock_list;
delete from model;

INSERT INTO `Stock_List` (`date_updated`, `fk_stock_status_id`) VALUES ((select DATE(now())), 3);
INSERT INTO `stock_file`
	(`update_id`, `file_name`, `date_of_last_update`, `fk_stock_list_id`)
VALUES
	(0,  'car_stock_0.json', (select DATE(now())), (select stock_list_id from stock_list where fk_stock_status_id = 3));		
    
INSERT INTO `model`
	(`model_vin`,`fk_manufacturer_id`, `model_name`, `retail_price`, `date_of_manufacture`, 
		`colour`, `transmission`, `horsepower`,
        `sunroof`, `alloy_wheels`, `ac`, `fk_stock_status_id`)
VALUES
	('FD95219FCS', 1, 'Focus', 22000, '2019-01-01',	'Red', 'Auto', 2000, 0, 1, 1,3),
	('FD95273FST', 1, 'Fiesta', 22000, '2019-05-04', 'Blue', 'Manual', 1400, 0, 0, 0,3);
-- RESET END

select * from model;

select * from stock_list;

select * from stock_status;

select * from stock_file;