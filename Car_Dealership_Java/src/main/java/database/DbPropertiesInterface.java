/**
 * 
 */
package database;

import java.util.Properties;

/**
 * @author Brown
 * Rules for getting and setting the properties for a specific instance of a Db.
 */
public interface DbPropertiesInterface {
	void setDefaultProperties();
	
	void setProperties(String url, String urlAndSchema, String userName, String password, 
			String dbSchema, String format);
	
	void setDbProperty(String key, String value);
	
	String getDbProperty(String key);
	
	Properties getDbProperties();
}
