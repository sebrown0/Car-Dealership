package departments.stock_department;

import enums.ErrorCodes;

public interface StockUpdateProcess {
	
	ErrorCodes checkForNewStock();
	ErrorCodes readStockFile();
	ErrorCodes updateStockList();
	
	default void updateStock() {				
		if(checkForNewStock() == ErrorCodes.NONE)
			if(readStockFile() == ErrorCodes.NONE)
				updateStockList();
	}
}
