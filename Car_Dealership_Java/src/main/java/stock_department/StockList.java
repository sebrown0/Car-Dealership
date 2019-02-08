/**
 * 
 */
package stock_department;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import containers.AppContainers.ListContainer;
import dao.SPARK_DB_DAO;
import dao.SparkDAO;
import database.Database;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import pojos.StockDetails;
import pojos.StockDetails.StockListTable;

/**
 * @author Steve Brown
 * Update the Stock_List and Stock_Updates tables.
 * Stock_List holds the car IDs' from the recent delivery.  
 * Stock_Updates holds data about the delivery.
 */
public class StockList {
	private SparkDAO spark;
	private Database dataBase;
	private String StockUpdateTable;
	private String fileNum;
	
	public StockList(SparkDAO spark, Database db, long fn, String StockUpdateTable) {
		super();
		this.spark = spark;
		this.dataBase = db;
		this.StockUpdateTable = StockUpdateTable;
		this.fileNum = String.valueOf(fn); // The next file in the sequence.
	}
	
	/*
	 * Attempt to update the tables.
	 * Create two lists to convert into DFs and then write them to the relevant table.
	 */
	public ErrorCodes update(Dataset<Row> deliveryDf) {
		StockDetails stockDetails = new StockDetails(fileNum, "1", StockUpdateTable);
		
		ListContainer<StockListTable> lcStockList = 
				new ListContainer<StockDetails.StockListTable>(stockDetails.getStockListTable());
		
		// Create DF with Bean
		Dataset<Row> listValuesDf = spark.session()
				.createDataFrame(lcStockList.getList(), stockDetails.getStockListTable().getClass())
				.withColumnRenamed("updateId", "stock_update_id")
				.withColumnRenamed("stockStatus", "status_id");
				
		// Prepare the list for the Stock List tbl before converting to DF.	
		ListContainer<pojos.StockDetails.StockUpdateTable> lcStockUpdate = 
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
			dataBase.writeDfToDBTable(fileDf, TableNames.STOCK_UPDATES.tblName());
			dataBase.writeDfToDBTable(stockListDf, TableNames.STOCK_LIST.tblName());
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage());
		}
		
		return ErrorCodes.NONE;
	}
		
}
