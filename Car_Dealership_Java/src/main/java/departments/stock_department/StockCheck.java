/**
 * 
 */
package departments.stock_department;

import java.io.File;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;

import dao.DatabaseDAO;
//import dao.SparkDfWrite;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import enums.DbProperties;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.FilePaths;
import enums.TableNames;
import spark.SparkDfReadInterface;
import spark.SparkDfReader;
import utils.logger.Log;
import utils.logger.Loggable;

/**
 * @author Steve Brown
 * 
 * Check the stock file directory to see if there is a new 'delivery' of stock.
 * The expected format of the filename is: 
 * 		car_stock_DELIVERY_NUMBER.json 
 * 		where DELIVERY_NUMBER is a number between 1 - 16777215 (medium int).
 * 
 *  The DB table TableNames.STOCK_UPDATES is checked for the next delivery number.
 *  If that file exists then the stock is updated/delivered. 
 */
public class StockCheck implements Loggable{
	
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;
	private Log log;
	private String stockFile = "";
	private long fileNum = 1;				// Default to first file.
	
	public StockCheck(DealerDAO dealerDAO) {
		super();
		this.spark = dealerDAO.getSpark();
		this.dataBase = dealerDAO.getDatabase();
		this.log = dealerDAO.getLog();
	}

	public ErrorCodes checkStockFile(){
		try {
			nextStockFile();
			if(!stockFile.isEmpty()) {
				if(FileHandler.checkStock(stockFile) == ErrorCodes.NONE) {
					log.logEntry(this, " New stock file to check: " + stockFile);
				}else {
					log.logEntry(this, " NO New file to check");
					return ErrorCodes.NO_FILE;
				}				
			}
		} catch (Throwable e) {
			return (ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage(), log));
		}		
		return ErrorCodes.NONE;
	}
	
	private void nextStockFile() throws Throwable {
		dataBase.setDbProperty(DbProperties.DB_TABLE.value(), TableNames.STOCK_UPDATES.tblName());
		SparkDfReadInterface stockUpdatesDf = new SparkDfReader(spark);
		stockUpdatesDf.readTable(spark, dataBase);
		Dataset<Row> idDf =  stockUpdatesDf.getDataFrame().agg(functions.max("update_id"));
		try {
			fileNum = idDf.head().getLong(0) + 1;
		}catch (Exception e) {
			log.logEntry(this, "No valid file id found using default (1)");
		}
		stockFile = FilePaths.CAR_STOCK_PATH.filePath() + "car_stock_" + fileNum + ".json";
	}
	
	public String getStockFile() {
		return stockFile;
	}
	
	public long getFileNum() {
		return fileNum;
	}
	
	private static class FileHandler{
		// Check to see if a 'new' stock file has arrived. 
		public static ErrorCodes checkStock(String filePath) {
			return (new File(filePath).exists()) ? ErrorCodes.NONE : ErrorCodes.NO_FILE; 
		}
	}

}
