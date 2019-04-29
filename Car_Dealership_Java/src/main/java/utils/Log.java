package utils;

/**
 * @author Steve Brown
 *
 */
public interface Log {
	void logEntry(Loggable logData, String logEntry);
//	void logEntry(String objId, String logEntry);
	void writeNewFile();
}