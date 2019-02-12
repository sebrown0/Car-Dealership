/**
 * 
 */
package stock_department;

import dao.SparkSessionDAO;
import database.Database;
import database.MySqlDB;
import enums.ErrorCodes;
import enums.TableNames;
import spark.Spark;

/**
 * @author Brown
 * Updates the stock if there is a new stock file.
 * Uses the 'rules' in StockUpdate and it's interface.
 */
public class UpdateStock extends StockUpdate {
	
	private SparkSessionDAO spark;
	private Database dataBase;
	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
	
	public UpdateStock() {
		// Create new spark session to use throughout the update process.
		spark = new Spark("StockDept-UpdateStock", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
	}
	
	public void beginUpdate() {
		updateStock();		// Start the process.		
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
