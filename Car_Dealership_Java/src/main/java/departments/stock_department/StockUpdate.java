package departments.stock_department;

import enums.ErrorCodes;

/**
 * @author Steve Brown
 * 
 * Dictates the order of the stock update process.
 */
public abstract class StockUpdate implements StockUpdateProcess{
	
	// Force the order of implementation.
	@Override
	public void updateStock() {				
		if(checkForNewStock() == ErrorCodes.NONE)
			if(readStockFile() == ErrorCodes.NONE)
				updateStockList();
	}

	// Force the implementation onto sub class (UpdateStock).
	@Override
	public abstract ErrorCodes checkForNewStock();
	@Override
	public abstract ErrorCodes readStockFile();
	@Override
	public abstract ErrorCodes updateStockList();
	
}
