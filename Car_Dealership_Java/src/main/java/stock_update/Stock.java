package stock_update;

import enums.ErrorCode;

public interface Stock {
	ErrorCode readStockFile();
	ErrorCode updateStockList();
}
