package dao;

import enums.ErrorCodes;
import utils.logger.Log;

/**
 * @author Steve Brown
 * Interface for file access. 
 */
	public interface FileDAO {

//		ErrorCodes openFile(String filePath);				// Open a file.
//		ErrorCodes writeFile(String text);					// Write some text to a file. Path specified elsewhere.
		
		ErrorCodes writeFile(String path, String text, Log log);		// Write some text to a file. 
		ErrorCodes checkStock(String filePath, Log log);				// Look for new stock file.
	}
