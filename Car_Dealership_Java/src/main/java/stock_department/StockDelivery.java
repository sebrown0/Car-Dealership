package stock_department;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkSessionDAO;
import database.Database;
import enums.DbProperties;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import spark.SparkDfReadInterface;
import spark.SparkDfReader;
import spark.SparkDfWriteInterface;
import spark.SparkDfWriter;

/**
 * @author Steve Brown
 * Read new stock (JSON file) and add new stock to the database. 
 */
public class StockDelivery{

	private SparkSessionDAO spark;
	private Database dataBase;
	private Dataset<Row> deliveryDf;
	
	public StockDelivery(SparkSessionDAO spark, Database db) {
		this.spark = spark;
		this.dataBase = db;
	}
	
	// Read the new stock file and write new details to the DB.
	public ErrorCodes readStockFile(String stockFile) {
		ErrorCodes eCode = ErrorCodes.NONE;
		
		// Read the new stock file into a Spark DF		
		SparkDfReadInterface dfCars = new SparkDfReader(spark);
		dfCars.readFile(stockFile, "json");

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
			eCode = (ErrorHandler.checkError(ErrorCodes.DUPLICATE_ENTRY, e.getMessage()));
		}
				
		return eCode;	
	}
			
	private ErrorCodes writeData(Dataset<Row> carStockDf) throws Throwable{
		
		try {
			deliveryDf = prepareModelDetails(carStockDf, spark);
			Dataset<Row> modelAttrDf = prepareModelAttrDetails(carStockDf);
			Dataset<Row> modelEnhDf = prepareModelExtraDetails(carStockDf);
			try {
				SparkDfWriteInterface dfWrite = new SparkDfWriter();
				if(dfWrite.writeDfToDbTable(deliveryDf, dataBase, TableNames.MODEL.tblName())) {
					if(dfWrite.writeDfToDbTable(modelAttrDf, dataBase, TableNames.MODEL_ATTR.tblName())) 
						dfWrite.writeDfToDbTable(modelEnhDf, dataBase, TableNames.MODEL_ENH.tblName());
				}
			} catch (Throwable e) {
				// Check for the most probable error 
				return (ErrorHandler.checkError(ErrorCodes.DUPLICATE_ENTRY, e.getMessage()));
			}
		} catch (Throwable e) {
			// Check for the most probable error 
			return (ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage()));
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
	private Dataset<Row> prepareModelDetails(Dataset<Row> carStockDf, SparkSessionDAO spark) throws Throwable {

		dataBase.setDbProperty(DbProperties.DB_TABLE.value(), TableNames.MANUFACTURER.tblName());
		SparkDfReadInterface manDf = new SparkDfReader(spark);
		manDf.readTable(spark, dataBase);
		

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
