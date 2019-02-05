package stock_department;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkDAO;
import database.DataBase;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import spark.SparkDfJSON;
import spark.SparkDfTable;

/**
 * @author Steve Brown
 * Read new stock (JSON file) and add new stock to the database. 
 */
public class StockDelivery{

	private SparkDAO spark;
	private DataBase dataBase;
	private Dataset<Row> deliveryDf;
	
	public StockDelivery(SparkDAO spark, DataBase db) {
		this.spark = spark;
		this.dataBase = db;
	}
	
	// Read the new stock file (in JSON format) and write new details to the DB.
	public ErrorCodes readStockFile(String stockFile) {
		ErrorCodes eCode = ErrorCodes.NONE;
		
		// Read the new stock file into a Spark DF		
		SparkDAO dfCars = new SparkDfJSON(spark, stockFile);

		// Get local ref to car df.
		Dataset<Row> carStockDf = dfCars.getDataFrame(); 

		// Get the extras from the sub category
		Dataset<Row> extrasDf =	carStockDf.select(carStockDf.col("vin"), 
				carStockDf.col("extras.alloy_wheels"), 
				carStockDf.col("extras.ac"), 
				carStockDf.col("extras.sunroof"));

		// Merge the extras with the car
		carStockDf.drop("extras.*");
		carStockDf = carStockDf.join(extrasDf, 
				extrasDf.col("vin").equalTo(carStockDf.col("vin")))
				.drop(extrasDf.col("vin"))
				.drop(carStockDf.col("extras"));

		try {
			eCode = writeData(carStockDf);
		} catch (Throwable e) {
			eCode = ErrorCodes.DF_ERROR;//(ErrorHandler.checkError(ErrorCodes.DUPLICATE_ENTRY, e.getCause().toString()));
		}
				
		return eCode;	
	}
			
	private ErrorCodes writeData(Dataset<Row> carStockDf) throws Throwable{
		
		try {
			deliveryDf = prepareModelDetails(carStockDf, spark);
			Dataset<Row> modelAttrDf = prepareModelAttrDetails(carStockDf);
			Dataset<Row> modelEnhDf = prepareModelExtraDetails(carStockDf);
			try {
				// Write all models' details, attributes and enhancements if previous successful. 			
				if(dataBase.writeDfToDBTable(deliveryDf, TableNames.MODEL.tblName())) {
					if(dataBase.writeDfToDBTable(modelAttrDf, TableNames.MODEL_ATTR.tblName())) 
						dataBase.writeDfToDBTable(modelEnhDf, TableNames.MODEL_ENH.tblName());
				}
			} catch (Throwable e) {
				// Check for the most probable error 
				return (ErrorHandler.checkError(ErrorCodes.DUPLICATE_ENTRY, e.getCause().toString()));
			}
		} catch (Throwable e) {
			// Check for the most probable error 
			return (ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getCause().toString()));
		}
		
		return ErrorCodes.NONE;			
	}
		
	// Prepare the model extras for writing.
	private Dataset<Row> prepareModelExtraDetails(Dataset<Row> carStockDf) {
		
		Dataset<Row> modelExtraDf = carStockDf
				.select("vin", "alloy_wheels", "ac", "sunroof")
				.withColumnRenamed("vin", "model_vin");
		
		return modelExtraDf;
	}
	
	// Prepare the model attributes for writing.
	private Dataset<Row> prepareModelAttrDetails(Dataset<Row> carStockDf) {
		
		Dataset<Row> modelAttrDf = carStockDf
				.select("vin", "colour", "horsepower", "transmission")
				.withColumnRenamed("vin", "model_vin");
		
		return modelAttrDf;
	}
		
	// Prepare the model details for writing
	private Dataset<Row> prepareModelDetails(Dataset<Row> carStockDf, SparkDAO spark)throws Throwable {

		dataBase.dBPropValue("dbtable", TableNames.MANUFACTURER.tblName());
		SparkDAO manDf = new SparkDfTable(spark, dataBase);
		
				
		// Get the manufacturer_id to go with the manufacturer.
		// Prepare the model df for appending to the model tbl.
		Dataset<Row> modelDf = carStockDf
				.select( "vin", "model_name", "manufacturer", "retail_price")
				.join( manDf.getDataFrame().select("manufacturer_id", "manufacturer_name"),  
						manDf.getDataFrame().col("manufacturer_name").equalTo(carStockDf.col("manufacturer")))
				.drop("manufacturer")
				.drop("manufacturer_name")
				.withColumnRenamed("vin", "model_vin");
		
		return modelDf;
	}
	
	public Dataset<Row> getDelivery(){
		return this.deliveryDf;
	}

}
