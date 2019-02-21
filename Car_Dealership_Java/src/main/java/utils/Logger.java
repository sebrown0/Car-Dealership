/**
 * 
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
					writeNewFile();
				}
			} catch (IOException e) {
				ErrorHandler.checkError(ErrorCodes.FILE_DELETE, e.getMessage());
			} 
		}
	}
	
	/* 
	 * Write a log entry to the log file. 
	 * Assuming once a log is created it is for one day only. 
	 * So don't write the date for each entry to keep it cleaner.
	 */
	@Override
	public void logEntry(String Id, String logEntry) {
		LocalDateTime localDt = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("HH:mm:ss(SSSS)");		
		this.writeFile(this.logFile, (dtFormat.format(localDt) + " : " + Id + " -> " + logEntry));
	}

	@Override
	public void writeNewFile() {
		LocalDateTime localDt = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.writeFile(this.logFile, (dtFormat.format(localDt)));
		logEntry("<Log>", "New File Started");
	}
}
