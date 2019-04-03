/**
 * 
 */
package file_handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dao.FileDAO;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import utils.Log;

/**
 * @author Steve Brown
 *
 */

public abstract class FileHandler implements FileDAO{

	/*
	 * (non-Javadoc)
	 * @see dao.FileDAO#checkStock(java.lang.String, utils.Log)
	 */
	@Override
	public ErrorCodes checkStock(String filePath, Log log) {
		return (new File(filePath).exists()) ? ErrorCodes.NONE : ErrorCodes.NO_FILE; 
	}

	/*
	 * (non-Javadoc)
	 * @see dao.FileDAO#writeFile(java.lang.String, java.lang.String, utils.Log)
	 */
	@Override
	public ErrorCodes writeFile(String filePath, String text, Log log) {
	    ErrorCodes eCode = ErrorCodes.NONE;
	    
		try (PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filePath,true)))){
			output.println(text);
		} catch (IOException e) {
			eCode =	ErrorHandler.checkError(ErrorCodes.FILE_ERROR, e.getMessage(), log);
		}
			    
		return eCode;
	}

}
