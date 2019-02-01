SELECT 
	man.manufacturer_name AS 'Manufacturer',
    modl.model_name AS 'Model',
    modl.retail_price AS 'MRRP',
    attr.colour AS 'Colour',
    attr.transmission AS 'Transmission',
    attr.horsepower AS 'Engine Size',
    enh.sunroof AS 'Sunroof',
    enh.alloy_wheels AS 'Alloy Wheels',
    enh.ac AS 'Air Conditioning'
FROM
	manufacturer man
INNER JOIN
	model modl
ON
	man.manufacturer_id = modl.manufacturer_id
INNER JOIN
	model_attributes attr
ON
	attr.model_vin = modl.model_vin
INNER JOIN
	model_enhancements enh
ON
	enh.model_vin = modl.model_vin;