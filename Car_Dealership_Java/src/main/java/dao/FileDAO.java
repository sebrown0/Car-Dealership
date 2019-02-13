package dao;

import enums.ErrorCodes;

/**
 * @author Steve Brown
 * Interface for file access. 
 */
	public interface FileDAO {
	
		ErrorCodes writeFile(String text);					// Write some text to a file. Path specified elsewhere.
		ErrorCodes writeFile(String path, String text);		// Write some text to a file. 
		ErrorCodes openFile(String filePath);				// Open a file.
		ErrorCodes checkStock(String filePath);				// Look for new stock file.
	}
