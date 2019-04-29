package utils;

/**
 * 
 * @author Steve Brown
 *
 * Any object that uses the log should implement this.
 * Pass this method as an argument to the LogEntry to give the 
 * writing object's ID.
 */
public interface Loggable {
	default String objectID() {	return "<" + this.getClass().getSimpleName() + ">";	}
//	String currentTime();
}
