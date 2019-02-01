package stock_department;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import dao.SparkDAO;
import database.DataBase;
import database.MySqlDB;
import enums.ErrorCode;
import enums.Files;
import enums.TableNames;
import spark.Spark;
import spark.SparkDfJSON;
import spark.SparkDfTable;

/**
 * @author Steve Brown
 * Check for new stock (JSON file) and add new stock to the database. 
 */
public class StockDelivery{

	private DataBase mySql;
	
	public StockDelivery() {
		//Default MySql DAO. Have to set db table before using.
		mySql = new MySqlDB(TableNames.MODEL.tblName());		
	}
	
//	@Override
	// Read the new stock file (in JSON format) and write new details to the DB.
	public ErrorCode readStockFile() {
		// Read the new stock file into a Spark DF		
		SparkDAO spark = new Spark("Spark1", "local", true);
		SparkDAO dfCars = new SparkDfJSON(spark, Files.CAR_STOCK.filePath());

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

		Dataset<Row> modelDf = prepareModelDetails(carStockDf, spark);
		Dataset<Row> modelAttrDf = prepareModelAttrDetails(carStockDf);
		Dataset<Row> modelEnhDf = prepareModelExtraDetails(carStockDf);
			
		try {
			// Write all models' details, attributes and enhancements if previous successful. 			
			if(mySql.writeDfToDBTable(modelDf, TableNames.MODEL.tblName()))
				if(mySql.writeDfToDBTable(modelAttrDf, TableNames.MODEL_ATTR.tblName())) 
					mySql.writeDfToDBTable(modelEnhDf, TableNames.MODEL_ENH.tblName());
		} catch (Throwable e) {
			System.out.println("**-- Couldn't write to DB. Possible duplicate entry --**");
			return ErrorCode.DUPLICATE_ENTRY;
		}
		return ErrorCode.NONE;	
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
	private Dataset<Row> prepareModelDetails(Dataset<Row> carStockDf, SparkDAO spark) {

		mySql.dBPropValue("dbtable", TableNames.MANUFACTURER.tblName());
		
		SparkDAO manDf = new SparkDfTable(spark, mySql);
				
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

}
