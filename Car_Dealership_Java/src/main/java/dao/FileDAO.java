package dao;

import enums.ErrorCode;

/**
 * @author Steve Brown
 * Interface for file access. 
 */
	public interface FileDAO {
	
		ErrorCode checkStock(String filePath);	// Look for new stock file.
	}
