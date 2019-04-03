package database;

import java.sql.Connection;
import java.util.Properties;

import enums.ErrorCodes;
import utils.Log;

/**
 * @author Steve Brown 
 *	Connection interface for a database object. 
 */

public interface DbConnectionInterface {
	// Connect using properties return status/error. 
	ErrorCodes connect(Properties dbProp, Log log);
	
	// Get the connection.
	Connection connection(Log log);
	
	// See is we're connected.
	boolean checkConnection(Log log);
	
}
