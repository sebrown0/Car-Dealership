/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
		System.out.println("Trying to connect to DB");
		try {
			this.conn = DriverManager.getConnection(
					dbProp.getProperty("url_schema"),
					dbProp.getProperty("username"),
					dbProp.getProperty("password"));
//					MySqlConn.URL_AND_SCHEMA.value(), MySqlConn.USERNAME.value(),
//					MySqlConn.PASSWORD.value());
			
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.DB_CONN, e.getMessage());
			return ErrorCodes.DB_CONN;
		}
		return ErrorCodes.NONE;
	}

	@Override
	public Connection connection() {
		if (conn == null)
			System.out.println("No DB Connection");

		return conn;
	}

}
