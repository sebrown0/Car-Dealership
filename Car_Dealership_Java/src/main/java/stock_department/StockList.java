/**
 * 
 */
package stock_department;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.hdfs.server.namenode.snapshot.FileWithSnapshot.FileDiff;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import dao.SparkDAO;
import database.DataBase;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;

/**
 * @author Brown
 *
 */
public class StockList {
	private SparkDAO spark;
	private DataBase dataBase;
	private String stockFile;
	private String fileNum;
	
	public StockList(SparkDAO spark, DataBase db, long fn, String stockFile) {
		super();
		this.spark = spark;
		this.dataBase = db;
		this.stockFile = stockFile;
		this.fileNum = String.valueOf(fn); // The next file in the sequence.
	}
	
	public ErrorCodes update(Dataset<Row> deliveryDf) {
			
		StockListValues stockListValues = new StockListValues();
		stockListValues.setFileDetail("1");	// Awaiting Preparation.
		stockListValues.setUpdateId(fileNum);	// The file number of the stock file we just read.
		
		// Create df with Bean
		Dataset<Row> listValuesDf = spark.session()
				.createDataFrame(createList(stockListValues), stockListValues.getClass());
		
		StockFile sf = new StockFile();
		sf.setFileDetail(stockFile);
		sf.setUpdateId(fileNum);

		Dataset<Row> fileDf = spark.session()
				.createDataFrame(createList(sf), sf.getClass())
				.withColumnRenamed("updateId", "update_id")
				.withColumnRenamed("fileDetail", "file_name");

		Dataset<Row> stockListDf = deliveryDf.select( "model_vin")
				.crossJoin(listValuesDf)
				.withColumnRenamed("updateId", "status_id")
				.withColumnRenamed("fileDetail", "stock_update_id");
		
		try {
			dataBase.writeDfToDBTable(fileDf, TableNames.STOCK_UPDATES.tblName());
			dataBase.writeDfToDBTable(stockListDf, TableNames.STOCK_LIST.tblName());
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage());
		}
		
		return ErrorCodes.NONE;
	}
	
	public static List<StockFile> createList(StockFile data){
		List<StockFile> fileData = new ArrayList<>();
		fileData.add(data);
		
		return fileData;	
	}
	
	public interface StockFileDetails{
		public String getFileDetail();
		public void setFileDetail(String data);
	}
	
	public static class StockListValues extends StockFile implements StockFileDetails, Serializable{
		
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
	
	public static class StockFile implements StockFileDetails, Serializable{
		
		private static final long serialVersionUID = 1L;
		private String fileName;
		private String updateId;
		
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
