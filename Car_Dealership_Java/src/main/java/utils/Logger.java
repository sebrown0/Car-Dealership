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

	private String logFile;
//	private static final String objId = "<Logger>"; // TODO

	private Logger() {}
	
	private void initialiseLogger(boolean deleteOld) {	
		this.logFile = FilePaths.LOG_PATH.filePath() + "CarDealer_Log.txt";
		
		if(deleteOld) {
			File file = new File(logFile);
			try {
				if(Files.deleteIfExists(file.toPath())) {
					writeNewFile();
				}
			} catch (IOException e) {
				ErrorHandler.checkError(ErrorCodes.FILE_DELETE, e.getMessage(), this);
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
		this.writeFile(this.logFile, (dtFormat.format(localDt) + " : " + Id + " -> " + logEntry), this);
	}

	@Override
	public void writeNewFile() {
		LocalDateTime localDt = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.writeFile(this.logFile, (dtFormat.format(localDt)), this);
		logEntry("<Log>", "New File Started");
	}
	
	public static class LogHelper {
		private static final Logger INSTANCE = new Logger();
		
		public static Logger logInstance(boolean deleteOld) {
			INSTANCE.initialiseLogger(deleteOld);
			return INSTANCE;
		}
	}
}
