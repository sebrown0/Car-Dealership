/**
 * 
 */
package stock_department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import enums.ErrorCodes;
import utils.Log;
import utils.Logger;

/**
 * @author Brown
 * Updates the stock if there is a new stock file.
 * Uses the 'rules' in StockUpdate and it's interface.
 */
public class UpdateStock extends StockUpdate {
	
	private final String objId;
	
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;
	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
	
//	private Log log = new Logger(false);
	
	public UpdateStock(String objId, SparkSessionDAO spark, DatabaseDAO dataBase ) {
		this.objId = objId + "<UpdateStock>";
		this.spark = spark;
		this.dataBase = dataBase;
//		// Create new spark session to use throughout the update process. TODO - Remove
//		spark = new Spark("StockDept-UpdateStock", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
//		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
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
