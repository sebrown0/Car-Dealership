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

/**
 * @author Steve Brown
 *
 */

public abstract class FileHandler implements FileDAO{

	@Override
	public ErrorCodes checkStock(String filePath) {
		return (new File(filePath).exists()) ? ErrorCodes.NONE : ErrorCodes.NO_FILE; 
	}

	@Override
	public ErrorCodes writeFile(String filePath, String text) {
	    ErrorCodes eCode = ErrorCodes.NONE;
	    
		try (PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(filePath,true)))){
			output.println(text);
		} catch (IOException e) {
			eCode =	ErrorHandler.checkError(ErrorCodes.FILE_ERROR, e.getMessage());
		}
			    
		return eCode;
	}

	@Override
	public ErrorCodes openFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCodes writeFile(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
