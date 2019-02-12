/**
 * 
 */
package stock_department;

/**
 * @author Brown
 *
 * Wrapper class for beans used in stock updates.
 */
public class StockDetails {
	
	private StockListTable stockListTable = new StockListTable();
	private StockUpdateTable stockUpdateTable = new StockUpdateTable();
	
	public StockDetails(String updateId, String stockStatus, String fileName) {

		this.stockListTable.setUpdateId(updateId);
		this.stockListTable.setStockStatus(stockStatus);;
		
		this.stockUpdateTable.setUpdateId(updateId);
		this.stockUpdateTable.setFileName(fileName);
	}

	
	public StockListTable getStockListTable() {
		return stockListTable;
	}

	public void setStockListTable(StockListTable stockListTable) {
		this.stockListTable = stockListTable;
	}

	public StockUpdateTable getStockUpdateTable() {
		return stockUpdateTable;
	}

	public void setStockUpdateTable(StockUpdateTable stockUpdateTable) {
		this.stockUpdateTable = stockUpdateTable;
	}


	/*
	 * Bean for creating a df to update the TableNames.STOCK_LIST table.
	 */
	public static class StockListTable {
		
		private String updateId;	// Number of the file just read.
		private String stockStatus;
		
		public String getUpdateId() {
			return updateId;
		}
		public void setUpdateId(String updateId) {
			this.updateId = updateId;
		}
		public String getStockStatus() {
			return stockStatus;
		}
		public void setStockStatus(String stockStatus) {
			this.stockStatus = stockStatus;
		}
		
	}
	
	/*
	 * Bean for creating a df to update the TableNames.STOCK_UPDATES table.
	 * UpdateId is the number of the file just read and is common to both tables. 
	 */
	public static class StockUpdateTable{
		
		private String fileName;	// Name of the file just read.
		private String updateId;	// Number of the file just read.
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getUpdateId() {
			return updateId;
		}
		public void setUpdateId(String updateId) {
			this.updateId = updateId;
		}
		
	}
}
