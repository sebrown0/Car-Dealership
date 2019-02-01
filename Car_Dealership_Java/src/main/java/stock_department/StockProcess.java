package stock_department;

import enums.ErrorCode;

public interface StockProcess {
	ErrorCode checkForNewStock();
	ErrorCode readStockFile();
	ErrorCode updateStockList();
}
