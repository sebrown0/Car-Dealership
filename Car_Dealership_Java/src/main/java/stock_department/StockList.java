/**
 * 
 */
package stock_department;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkDAO;
import database.DataBase;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;

/**
 * @author Steve Brown
 * Update the Stock_List and Stock_Updates tables.
 * Stock_List holds the car IDs' from the recent delivery.  
 * Stock_Updates holds data about the delivery.
 */
public class StockList {
	private SparkDAO spark;
	private DataBase dataBase;
	private String StockUpdateTable;
	private String fileNum;
	
	public StockList(SparkDAO spark, DataBase db, long fn, String StockUpdateTable) {
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
			
		// Prepare the list for the Stock List tbl before converting to DF.
		StockListTable stockListValues = new StockListTable();
		stockListValues.setFileDetail("1");		// Awaiting Preparation.
		stockListValues.setUpdateId(fileNum);	// The file number of the stock file we just read.
		
		// Create DF with Bean
		Dataset<Row> listValuesDf = spark.session()
				.createDataFrame(createList(stockListValues), stockListValues.getClass());
		
		// Prepare the list for the Stock Update tbl before converting to DF.
		StockUpdateTable sf = new StockUpdateTable();
		sf.setFileDetail(StockUpdateTable);
		sf.setUpdateId(fileNum);

		// Create DF with Bean
		Dataset<Row> fileDf = spark.session()
				.createDataFrame(createList(sf), sf.getClass())
				.withColumnRenamed("updateId", "update_id")
				.withColumnRenamed("fileDetail", "file_name");

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
		
	/*
	 * Create a list of stock details so a df can be created
	 * to update the TableNames.STOCK_UPDATES & TableNames.STOCK_LIST tables.
	 */
	public static List<StockUpdateTable> createList(StockUpdateTable data){
		List<StockUpdateTable> fileData = new ArrayList<>();
		fileData.add(data);
		
		return fileData;	
	}
	
	/*
	 * Used as getter and setter for Stock Table updates.
	 */
	public interface StockUpdateTableDetails{
		public String getFileDetail();
		public void setFileDetail(String data);
	}
	
	/*
	 * Bean for creating a df to update the TableNames.STOCK_LIST table.
	 */
	public static class StockListTable extends StockUpdateTable implements StockUpdateTableDetails, Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private String stockStatus;

		@Override
		public String getFileDetail() {
			return this.stockStatus;
		}

		@Override
		public void setFileDetail(String data) {
			this.stockStatus = data;
		}					
	}
	
	/*
	 * Bean for creating a df to update the TableNames.STOCK_UPDATES table.
	 * UpdateId is the number of the file just read and is common to both tables. 
	 */
	public static class StockUpdateTable implements StockUpdateTableDetails, Serializable{
		
		private static final long serialVersionUID = 1L;
		private String fileName;	// Name of the file just read.
		private String updateId;	// Number of the file just read.
		
		public String getUpdateId() {
			return updateId;
		}

		public void setUpdateId(String uid) {
			this.updateId = uid;
		}

		@Override
		public String getFileDetail() {
			return this.fileName;
		}

		@Override
		public void setFileDetail(String data) {
			this.fileName = data;
		}
		
	}
	
}
