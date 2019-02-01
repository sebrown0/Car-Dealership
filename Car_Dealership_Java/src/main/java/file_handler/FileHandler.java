/**
 * 
 */
package file_handler;

import java.io.File;

import dao.FileDAO;
import enums.ErrorCode;

/**
 * @author Steve Brown
 *
 */

public class FileHandler implements FileDAO{

	@Override
	public ErrorCode checkStock(String filePath) {
		return (new File(filePath).exists()) ? ErrorCode.NONE : ErrorCode.NO_FILE; 
	}

}
