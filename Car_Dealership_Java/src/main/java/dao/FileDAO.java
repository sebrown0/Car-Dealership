package dao;

import enums.ErrorCodes;
import utils.logger.Log;

/**
 * @author Steve Brown
 * Interface for file access. 
 */
public interface FileDAO {
	
	// Write some text to a file.
	ErrorCodes writeFile(String path, String text, Log log);		
	
	// Look for new stock file.
	ErrorCodes checkStock(String filePath, Log log);				
}
