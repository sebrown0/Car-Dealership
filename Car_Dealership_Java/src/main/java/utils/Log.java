package utils;

public interface Log {

//	void write(String objId, String logEntry);
	void logEntry(String objId, String logEntry);
	void writeNewFile();

}