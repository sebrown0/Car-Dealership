/**
 * 
 */
package stock_department;

import enums.ErrorCode;

/**
 * @author Brown
 *
 */
public class StockDept extends StockUpdate {

	public StockDept() {
		updateStock();
	}
	
	@Override
	public ErrorCode checkForNewStock() {
		StockCheck sc = new StockCheck();
		
		return (sc.checkStockFile());
	}

	@Override
	public ErrorCode readStockFile() {
		
		StockDelivery stock = new StockDelivery();
		stock.readStockFile();
		
		return ErrorCode.NONE;
	}

	@Override
	public ErrorCode updateStockList() {

		
		return ErrorCode.NONE;
	}
	

	
}
