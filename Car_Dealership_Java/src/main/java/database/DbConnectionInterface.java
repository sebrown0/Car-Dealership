package database;

import java.sql.Connection;
import java.util.Properties;

import enums.ErrorCodes;

/**
 * @author Steve Brown 
 *	Connection interface for a database object. 
 */

public interface DbConnectionInterface {
	// Connect using properties return status/error. 
	ErrorCodes connect(Properties dbProp);
	
	// Get the connection.
	Connection connection();
	
	// See is we're connected.
	boolean checkConnection();
}
