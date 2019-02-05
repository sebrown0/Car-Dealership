/**
 * 
 */
package stock_department;

import dao.SparkDAO;
import database.DataBase;
import database.MySqlDB;
import enums.ErrorCodes;
import enums.TableNames;
import spark.Spark;

/**
 * @author Brown
 *
 */
public class StockDept extends StockUpdate {

	private SparkDAO spark;
	private DataBase dataBase;
	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
	
	public StockDept() {
		// Create new spark session to use throughout the update process.
		spark = new Spark("Spark1", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
		// Start the process. <<<<<<<<<<<<<<<<doesn't have to be here>>>>>>>>>>>>>>>>>.
		updateStock(); 
	}
	
	@Override
	public ErrorCodes checkForNewStock() {
		stockCheck = new StockCheck(spark, dataBase);
		return (stockCheck.checkStockFile()); 
	}

	@Override
	public ErrorCodes readStockFile() {
		
		stockDelivery = new StockDelivery(spark, dataBase);
		return (stockDelivery.readStockFile(this.stockCheck.getStockFile()));
	}

	@Override
	public ErrorCodes updateStockList() {

		stockList = new StockList(spark, dataBase, stockCheck.getFileNum(), stockCheck.getStockFile());
		stockList.update(stockDelivery.getDelivery());
		return ErrorCodes.NONE;
	}
	

	
}
