/**
 * 
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.FilePaths;
import file_handler.FileHandler;

/**
 * @author Steve Brown
 *
 */
public final class Logger extends FileHandler implements Log{

	private final String logFile;
	private static final String objId = "<Logger>";

	public Logger(boolean deleteOld) {
		
		this.logFile = FilePaths.LOG_PATH.filePath() + "CarDealer_Log.txt";
		
		if(deleteOld) {
			File file = new File(logFile);
			try {
				if(Files.deleteIfExists(file.toPath())) {
					write(objId, "New File");
				}
			} catch (IOException e) {
				ErrorHandler.checkError(ErrorCodes.FILE_DELETE, e.getMessage());
			} 
		}
	}
	
	/* 
	 * Write a log entry to the log file. 
	 */
	@Override
	public void write(String Id, String logEntry) {
		
		this.writeFile(this.logFile, (LocalDateTime.now() + " : " + Id + " " + logEntry));
	}
}
