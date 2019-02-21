package stock_department;

import enums.ErrorCodes;

/**
 * @author Steve Brown
 * 
 * Dictates the order of the stock update process.
 */
public abstract class StockUpdate implements StockUpdateProcess{
	
	// Force the order of implementation.
	public void updateStock() {				
		if(checkForNewStock() == ErrorCodes.NONE)
			if(readStockFile() == ErrorCodes.NONE)
				updateStockList();
	}

	// Force the implementation onto sub class (UpdateStock).
	public abstract ErrorCodes checkForNewStock();
	public abstract ErrorCodes readStockFile();
	public abstract ErrorCodes updateStockList();
	
}
