package stock_department;

import enums.ErrorCodes;

public abstract class StockUpdate implements StockProcess{

	public void updateStock() {
		if(checkForNewStock() == ErrorCodes.NONE)
			if(readStockFile() == ErrorCodes.NONE)
				updateStockList();
	}

	// Force the implementation onto sub class (StockDept)
	public abstract ErrorCodes checkForNewStock();
	public abstract ErrorCodes readStockFile();
	public abstract ErrorCodes updateStockList();
	
}
