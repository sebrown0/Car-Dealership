/**
 * 
 */
package file_handler;

import java.io.File;

import dao.FileDAO;
import enums.ErrorCodes;

/**
 * @author Steve Brown
 *
 */

public class FileHandler implements FileDAO{

	@Override
	public ErrorCodes checkStock(String filePath) {
		return (new File(filePath).exists()) ? ErrorCodes.NONE : ErrorCodes.NO_FILE; 
	}

}
