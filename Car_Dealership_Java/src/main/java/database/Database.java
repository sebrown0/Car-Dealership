package database;

import java.sql.Connection;
import java.util.Properties;

import dao.SPARK_DB_DAO;
import enums.CD_Schema;
import enums.ErrorCodes;
/**
 * @author Steve Brown
 * Super class that allows data base access.
 */

public abstract class Database implements SPARK_DB_DAO, DbPropertiesInterface {

	private Properties dbProp;			// Properties needed to connect to the DB.
	private DbConnection dbConnection;	// DB Connection

			
	public Database() {
		this.dbProp = new Properties();
		this.dbConnection = new DbConnection();
	}
	
	// Implementation of methods common to all DBs - Start.
	@Override
	public void setDbProperty(String key, String value) {
		
		System.out.println("@Override - public void setDbProperty(String key, String value) {");
		
		if(key == "dbtable") { 	// Add the schema name to be sure of correct tbl.
			this.dbProp.setProperty(key,
					CD_Schema.SCHEMA.value() + "." + value);
		}else {
			this.dbProp.setProperty(key, value);
		}		
	}
	
	@Override
	public String getDbProperty(String key) {
		return dbProp.getProperty(key);
	}
	
	@Override
	public Properties getDbProperties() {
		return dbProp;
	}
	
	@Override
	public void setProperties(String url, String urlAndSchema, String userName, String password, String dbSchema,
			String format) {
		// Using default.
		
	}
	
	public Connection connection() {
		if(this.dbConnection.connect(this.dbProp) == ErrorCodes.NONE) {
			return dbConnection.connection();	
		}else {
			return null;
		}
	}
	// Implementation of methods common to all DBs - End.

	
	
	
}
