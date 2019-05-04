/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import enums.Schemas;
import enums.MySqlConn;

public class ConnectionPool {
	
	private static ConnectionPool INSTANCE = null;
	private HikariDataSource ds = null;

	static {
		try {
//			LOG.info("Initializing the connection pool ... ");
			INSTANCE = new ConnectionPool();
//			LOG.info("Connection pool initialized successfully.");
		} catch (Exception e) {
//			LOG.error("Exception when trying to initialize the connection pool",e);
		}
		
	}

	private ConnectionPool() {
		ds = new HikariDataSource();
		ds.setMaximumPoolSize(100);
		
		ds.setJdbcUrl(MySqlConn.URL_AND_SCHEMA.value());
		ds.addDataSourceProperty("serverName", MySqlConn.URL.value());
		ds.addDataSourceProperty("databaseName", Schemas.SCHEMA.value());
		ds.addDataSourceProperty("user", MySqlConn.USERNAME.value());
		ds.addDataSourceProperty("password", MySqlConn.PASSWORD.value());	
	}

	public static ConnectionPool getInstance() {
		return INSTANCE;
	}

	public void closeDS() {
		ds.close();
	}
	
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}

