package database;

import enums.CD_Schema;
import enums.DbProperties;
import enums.MySqlConn;

/**
 * @author Steve Brown
 * Sets up default values for the MySql DB used in Car Dealership.
 */
public class MySqlDB extends Database{

	public MySqlDB() {
		setDefaultProperties();
	}
	
	// Setup default connection properties for MySql with JDBC
	public MySqlDB(String tblName) {
		super();
		setDefaultProperties();
	}
	
	@Override
	public void setDefaultProperties() {
		this.setDbProperty(DbProperties.URL.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.URL_AND_SCHEMA.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.USER_NAME.value(), MySqlConn.USERNAME.value());
		this.setDbProperty(DbProperties.PASSWORD.value(), MySqlConn.PASSWORD.value());
		this.setDbProperty(DbProperties.SCHEMA.value(), CD_Schema.SCHEMA.value());
		//TODO change default table
		this.setDbProperty(DbProperties.DB_TABLE.value(), "");
		this.setDbProperty(DbProperties.FORMAT.value(), "jdbc");
	}

}
