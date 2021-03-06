package database;

import enums.DbProperties;
import enums.MySqlConn;
import enums.Schemas;
import utils.logger.Log;

/**
 * @author Steve Brown 
 * 
 *  Sets up default values for the MySql DB used in Car Dealership.
 *  
 */
public class MySqlDB extends Database {

	public MySqlDB(Log log) {
		super(log);
		/* 
		 * TODO - 	Connection is hard coded in the Connection Pool as MySql.
		 * 			Change it so that an instance of a DB (i.e. MySql) is created.
		 */
		setDefaultProperties();
	}

//	public HikariConfig initialiseConnectionPool() {
//		HikariConfig config = new HikariConfig();
//
//        config.setJdbcUrl(MySqlConn.URL_AND_SCHEMA.value());
//        config.setUsername(MySqlConn.USERNAME.value());
//        config.setPassword(MySqlConn.USERNAME.value() );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        config.setPoolName("Car_Dealer_Pool");
//        config.setMaximumPoolSize(25);
//        config.setMinimumIdle(2);        
//        
//        return config;
//	}

	public void setDefaultProperties() {
		this.setDbProperty(DbProperties.URL.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.URL_AND_SCHEMA.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.USER_NAME.value(), MySqlConn.USERNAME.value());
		this.setDbProperty(DbProperties.PASSWORD.value(), MySqlConn.PASSWORD.value());
		this.setDbProperty(DbProperties.SCHEMA.value(), Schemas.SCHEMA.value());
		this.setDbProperty(DbProperties.DB_TABLE.value(), "");
		this.setDbProperty(DbProperties.FORMAT.value(), "jdbc");
	}
}
