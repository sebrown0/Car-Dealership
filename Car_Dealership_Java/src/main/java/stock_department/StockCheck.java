/**
 * 
 */
package stock_department;

import java.io.File;

import enums.ErrorCode;
import enums.Files;

/**
 * @author Brown
 *
 */
public class StockCheck {

	public ErrorCode checkStockFile(){
		if(FileHandler.checkStock(Files.CAR_STOCK.filePath()) == ErrorCode.NONE) {
			System.out.println("New file to check");
			return ErrorCode.NONE;
		}else {
			System.out.println("NO New file to check");
			return ErrorCode.NO_FILE;
		}
	}
	
	private static class FileHandler{

		public static ErrorCode checkStock(String filePath) {
			return (new File(filePath).exists()) ? ErrorCode.NONE : ErrorCode.NO_FILE; 
		}

	}

}
