/**
 * 
 */
package utils.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.FilePaths;
import file_handler.FileHandler;
import timer.Timer;

/**
 * @author Steve Brown
 *
 */
public final class Logger extends FileHandler implements Log {

	private String logFile;
	private Timer timer;
	
	private Logger() {}
	
	private void initialiseLogger(boolean deleteOld, Timer timer) {	
		this.logFile = FilePaths.LOG_PATH.filePath() + "CarDealer_Log.txt";
		this.timer = timer;
		
		if(deleteOld) {
			File file = new File(logFile);
			try {
				if(Files.deleteIfExists(file.toPath())) 
					writeNewFile();
			} catch (IOException e) {
				ErrorHandler.checkError(ErrorCodes.FILE_DELETE, e.getMessage(), this);
			} 
		}
	}

	@Override
	public void logEntry(Loggable logData, String logEntry) {
		writeFile(logFile, (formatLogData(logData) + logEntry), this);
	}	
	
	private String formatLogData(Loggable logData) {
		return timer.currentTimeFormatted() + ": " + logData.objectID() + " -> ";
	}
	
	@Override
	public void writeNewFile() {
		LocalDateTime localDt = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.writeFile(this.logFile, (dtFormat.format(localDt)), this);
	}
	
	public static class LogHelper {
		private static final Logger INSTANCE = new Logger();
		
		public static Logger logInstance(boolean deleteOld, Timer timer) {
			INSTANCE.initialiseLogger(deleteOld, timer);
			return INSTANCE;
		}
	}

}
