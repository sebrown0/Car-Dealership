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
import enums.MySqlConn;

/**
 * @author Brown Provides a DB connection for the calling class.
 */
public class DbConnection implements DbConnectionInterface {

	private Connection conn = null;

	@Override
	public ErrorCodes connect(Properties dbProp) {
		try {
			this.conn = DriverManager.getConnection(
					dbProp.getProperty(DbProperties.URL_AND_SCHEMA.value()),
					dbProp.getProperty(DbProperties.USER_NAME.value()),
					dbProp.getProperty(DbProperties.PASSWORD.value()));
			
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.DB_CONN, e.getMessage());
			return ErrorCodes.DB_CONN;
		}
		return ErrorCodes.NONE;
	}

	@Override
	public Connection connection() {
		//TODO - error codes 
		if (conn == null)
			System.out.println("No DB Connection");

		return conn;
	}

}
