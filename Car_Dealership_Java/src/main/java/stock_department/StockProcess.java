package stock_department;

import enums.ErrorCodes;

public interface StockProcess {
	ErrorCodes checkForNewStock();
	ErrorCodes readStockFile();
	ErrorCodes updateStockList();
}
