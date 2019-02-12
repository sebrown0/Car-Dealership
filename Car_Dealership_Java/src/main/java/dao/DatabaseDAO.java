/**
 * 
 */
package dao;

import java.util.Properties;

import database.DbConnectionInterface;
import database.StoredProcedure;

/**
 * @author Brown
 * Rules for getting and setting the properties for a specific instance of a Db.
 */
public interface DatabaseDAO {
	// Sets hard coded values for a DB instance.
	void setDefaultProperties();
	
	// Define the properties for a DB instance.
	void setProperties(String url, String urlAndSchema, String userName, String password, 
			String dbSchema, String format);
	
	// Change a property for a DB instance.
	void setDbProperty(String key, String value);
	
	// Get a property for a DB instance.
	String getDbProperty(String key);
	
	// Get all the properties for a DB instance.
	Properties getDbProperties();
	
	// Get a connection interface for a DB instance.
	DbConnectionInterface dbConnection();
	
	// Connect to Database using a DB instance.
	void dbConnect();
	
	// Execute a stored procedure on DB instance.
	StoredProcedure executeSP(String query);
}
