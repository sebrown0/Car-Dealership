/*
** Procedure to find all a model's details for a given man & model i.e. Ford Focus
*/
CREATE PROCEDURE `GetModelDetails`(
	IN manufacturer VARCHAR(45),
    IN model_name VARCHAR(45)
	)
BEGIN
	SELECT 
		man.manufacturer_name AS 'Manufacturer', 
		md.model_name AS 'Model', md.retail_price AS 'MAN RRP',
		attr.colour AS 'Colour', attr.transmission AS 'Transmission', attr.horsepower AS 'HP',
		enh.sunroof AS 'Sunroof', enh.alloy_wheels AS 'Alloy Wheels', enh.ac AS 'A/C'
	FROM
		manufacturer man
	INNER JOIN
		model md
	ON
		man.manufacturer_id = md.manufacturer_id
	INNER JOIN
		model_attributes attr
	ON 
		attr.model_vin = md.model_vin
	INNER JOIN
		model_enhancements enh
	ON 
		enh.model_vin = md.model_vin
	WHERE
		man.manufacturer_name = manufacturer AND md.model_name = model_name;
END;