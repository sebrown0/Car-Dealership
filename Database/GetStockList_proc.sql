CREATE PROCEDURE `GetStockList` ()
BEGIN
SELECT  
	mdl.model_vin AS vin, manufacturer_name AS manufacturer , model_name,
    horsepower, colour, transmission, ac, sunroof, alloy_wheels
FROM
	stock_list sl	 
JOIN
	model mdl
ON
	mdl.model_vin = sl.model_vin
JOIN
	model_attributes attr
ON
	attr.model_vin = sl.model_vin
JOIN
	model_enhancements enh
ON
	enh.model_vin = sl.model_vin
JOIN
	manufacturer man
ON
	man.manufacturer_id = mdl.manufacturer_id;
END