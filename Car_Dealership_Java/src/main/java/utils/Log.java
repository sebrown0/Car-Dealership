package utils;

/**
 * @author Steve Brown
 *
 */
public interface Log {
	void logEntry(String objId, String logEntry);
	void writeNewFile();
}