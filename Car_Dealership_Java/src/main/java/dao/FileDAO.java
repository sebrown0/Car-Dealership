package dao;

import enums.ErrorCodes;

/**
 * @author Steve Brown
 * Interface for file access. 
 */
	public interface FileDAO {
	
		ErrorCodes checkStock(String filePath);	// Look for new stock file.
	}
