/**
 * 
 */
package stock_department;

import java.io.File;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;

import dao.DatabaseDAO;
//import dao.SparkDfWrite;
import dao.SparkSessionDAO;
import enums.DbProperties;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.FilePaths;
import enums.TableNames;
import spark.SparkDfReadInterface;
import spark.SparkDfReader;
//import spark.SparkDfTable;
import utils.Log;
import utils.Logger;

/**
 * @author Brown
 * Check the stock file directory to see if there is a new 'delivery' of stock.
 * The expected format of the filename is: 
 * 		car_stock_DELIVERY_NUMBER.json 
 * 		where DELIVERY_NUMBER is a number between 1 - 16777215 (medium int).
 * 
 *  The DB table TableNames.STOCK_UPDATES is checked for the next delivery number.
 *  If that file exists then the stock is updated/delivered. 
 */
public class StockCheck {

	private static final String objId = "<Stock-Dept><StockCheck>";
	
	private SparkSessionDAO spark = null;
	private DatabaseDAO dataBase = null;
	private String stockFile = "";
	private long fileNum = 0;
	
	private Log log = new Logger(false);	// TODO - Logger
	
	public StockCheck(SparkSessionDAO spark, DatabaseDAO dataBase) {
		super();
		this.spark = spark;
		this.dataBase = dataBase;
	}

	public ErrorCodes checkStockFile(){
		
		try {
			nextStockFile();
			log.write(objId, "File to check for:" + stockFile); 
			if(!stockFile.isEmpty()) {
				if(FileHandler.checkStock(stockFile) == ErrorCodes.NONE) {
					System.out.println("New file to check:" + stockFile);
				}else {
					System.out.println("NO New file to check");
					return ErrorCodes.NO_FILE;
				}				
			}
		} catch (Throwable e) {
			// Check for the most probable error 
			return (ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage()));
		}
		
		return ErrorCodes.NONE;
	}
	
	private void nextStockFile() throws Throwable {
		
		dataBase.setDbProperty(DbProperties.DB_TABLE.value(), TableNames.STOCK_UPDATES.tblName());
		SparkDfReadInterface stockUpdatesDf = new SparkDfReader(spark);
		stockUpdatesDf.readTable(spark, dataBase);

		Dataset<Row> idDf =  stockUpdatesDf.getDataFrame().agg(functions.max("update_id"));
		fileNum = idDf.head().getLong(0) + 1;
		stockFile = FilePaths.CAR_STOCK_PATH.filePath() + "car_stock_" + fileNum + ".json";

	}
	
	public String getStockFile() {
		return stockFile;
	}
	
	public long getFileNum() {
		return fileNum;
	}
	
	private static class FileHandler{
		//TODO - DELETE OLD FILES??????
		
		// Check to see if a 'new' stock file has arrived. 
		public static ErrorCodes checkStock(String filePath) {
			return (new File(filePath).exists()) ? ErrorCodes.NONE : ErrorCodes.NO_FILE; 
		}

	}

}
