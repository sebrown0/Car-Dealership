/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import enums.MySqlConn;
import enums.Schemas;

/**
 * 
 * @author Steve Brown
 *
 * Connection pool using HikariCP.
 */
public class ConnectionPool {
	
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds = null;
	
	static {
        config.setJdbcUrl(MySqlConn.URL_AND_SCHEMA.value());
        config.setUsername(MySqlConn.USERNAME.value());
        config.setPassword(MySqlConn.PASSWORD.value());
		config.addDataSourceProperty("serverName", MySqlConn.URL.value());
		config.addDataSourceProperty("databaseName", Schemas.SCHEMA.value());
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.setPoolName("DealerConnectionPool");
        config.setMaximumPoolSize(15);
        config.setMinimumIdle(2);
        
        ds = new HikariDataSource(config);
	}
	
	private ConnectionPool() {}
	
	public void poolSize(int pSize) {
		ds.setMaximumPoolSize(pSize);
	}

	public static void closeDS() {
		ds.close();
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}

