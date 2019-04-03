/**
 * 
 */
package departments.stock_department;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import containers.AppContainers.ListContainer;
import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import departments.stock_department.StockDetails.StockListTable;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import spark.SparkDataFramefWriter;
import spark.SparkDfWriter;
import utils.Log;

/**
 * @author Steve Brown
 * Update the Stock_List and Stock_Updates tables.
 * Stock_List holds the car IDs' from the recent delivery.  
 * Stock_Updates holds data about the delivery.
 */
public class StockList {
	
	private static final String objId = "<Stock-Dept> <StockList>";
	private Log log;
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;
	private String StockUpdateTable;
	private String fileNum;
	
	public StockList(DealerDAO dealerDAO, long fn, String StockUpdateTable) {
		super();
		this.spark = dealerDAO.getSpark();
		this.dataBase = dealerDAO.getDatabase();
		this.log = dealerDAO.getLog();
		this.StockUpdateTable = StockUpdateTable;
		this.fileNum = String.valueOf(fn); // The next file in the sequence.
	}
	
	/*
	 * Attempt to update the tables.
	 * Create two lists to convert into DFs and then write them to the relevant table.
	 */
	public ErrorCodes update(Dataset<Row> deliveryDf) {
		log.logEntry(objId, "Updating stock list");
		StockDetails stockDetails = new StockDetails(fileNum, "1", StockUpdateTable); // TODO - Stock status = 1
		
		ListContainer<StockListTable> lcStockList = 
				new ListContainer<StockDetails.StockListTable>(stockDetails.getStockListTable());
		
		// Create DF with Bean
		Dataset<Row> listValuesDf = spark.session()
				.createDataFrame(lcStockList.getList(), stockDetails.getStockListTable().getClass())
				.withColumnRenamed("updateId", "stock_update_id")
				.withColumnRenamed("stockStatus", "status_id");
				
		// Prepare the list for the Stock List tbl before converting to DF.	
		ListContainer<departments.stock_department.StockDetails.StockUpdateTable> lcStockUpdate = 
				new ListContainer<StockDetails.StockUpdateTable>(stockDetails.getStockUpdateTable());
		
		// Create DF with Bean
		Dataset<Row> fileDf = spark.session()
				.createDataFrame(lcStockUpdate.getList(), stockDetails.getStockUpdateTable().getClass())
				.withColumnRenamed("updateId", "update_id")
				.withColumnRenamed("fileName", "file_name");
		
		// Join the Stock List details with the delivery vins' for each car.
		Dataset<Row> stockListDf = deliveryDf.select( "model_vin")
				.crossJoin(listValuesDf)
				.withColumnRenamed("updateId", "status_id")
				.withColumnRenamed("fileDetail", "stock_update_id");
		

		try { // Update the tables.
			SparkDataFramefWriter writeDf = new SparkDfWriter();
			writeDf.writeDfToDbTable(fileDf, dataBase, TableNames.STOCK_UPDATES.tblName());
			writeDf.writeDfToDbTable(stockListDf, dataBase, TableNames.STOCK_LIST.tblName());

		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage(), log);
		}
		
		return ErrorCodes.NONE;
	}
		
}
