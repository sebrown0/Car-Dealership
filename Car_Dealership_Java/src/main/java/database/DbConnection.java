/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import enums.DbProperties;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import utils.logger.Log;

/**
 * @author Steve Brown 
 * 
 * Provides a DB connection for the calling class.
 * Inputs:
 * 	Properties - db connection properties.
 * 
 * Returns:
 * 	ErrorCodes - Status of a connection attempt (see enums.Errorcodes).
 * 	Connection - The database connection.
 * 	boolean - Non specific connection status.
 * 	
 */
public class DbConnection implements DbConnectionInterface {

	private Connection conn = null;
	private boolean dbConnected = false;
	
	@Override
	public ErrorCodes connect(Properties dbProp, Log log) {
		if (!dbConnected) {
			try {
				this.conn = DriverManager.getConnection(
						dbProp.getProperty(DbProperties.URL_AND_SCHEMA.value()),
						dbProp.getProperty(DbProperties.USER_NAME.value()),
						dbProp.getProperty(DbProperties.PASSWORD.value()));
				dbConnected = true;
			} catch (SQLException e) {
				ErrorHandler.checkError(ErrorCodes.DB_CONN, e.getMessage(), log);
				return ErrorCodes.DB_CONN;
			}
		}
		return ErrorCodes.NONE;
	}

	@Override
	public Connection connection(Log log) {
		//TODO - error codes 
		if (!dbConnected)
			System.out.println("No DB Connection");

		return conn;
	}

	@Override
	public boolean checkConnection(Log log) {
		return dbConnected;
	}

}
