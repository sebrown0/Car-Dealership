package stock_department;

import enums.ErrorCode;

public abstract class StockUpdate implements StockProcess{

	public void updateStock() {
		if(checkForNewStock() == ErrorCode.NONE)
			if(readStockFile() == ErrorCode.NONE);
//		updateStockList();
	}

	// Force the implementation onto sub class (StockDept)
	public abstract ErrorCode checkForNewStock();
	public abstract ErrorCode readStockFile();
	public abstract ErrorCode updateStockList();
	
}
