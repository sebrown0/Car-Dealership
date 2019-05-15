package dao;

import java.util.Properties;

/**
 * @author Steve Brown
 *
 */
public interface DBProperty {
		
	// Get a property for a DB instance.
	String getDbProperty(String key);
		
	// Get all the properties for a DB instance.
	Properties getDbProperties();
	
	// Change a property for a DB instance.
	void setDbProperty(String key, String value);
}
