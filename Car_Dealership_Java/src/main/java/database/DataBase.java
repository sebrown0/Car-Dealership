package database;

import java.sql.Connection;
import java.util.Properties;

import dao.DB_DAO;
import enums.CD_Schema;
/**
 * @author Steve Brown
 * Super class that allows data base access.
 */

public abstract class DataBase implements DB_DAO {

	private Properties dbProp;			// Properties needed to connect to the DB. 
	private Connection dbConnection;	// DB Connection
			
	public DataBase() {
		this.dbProp = new Properties();
	}
	
	public DataBase(String url, String urlSchema, String user, String password, 
			String dbSchema, String tblName, String format) {
		
		this.dbProp = new Properties();
		this.dbProp.setProperty("url", url);
		this.dbProp.setProperty("url_schema", urlSchema);
		this.dbProp.setProperty("username", user);
		this.dbProp.setProperty("password", password);
		this.dbProp.setProperty("schema", dbSchema);
		this.dbProp.setProperty("dbtable", tblName);
		this.dbProp.setProperty("format", format);
	}

	public String dBPropValue(String key) {
		return dbProp.getProperty(key);
	}
	
	public void dBPropValue(String key, String value) {
		if(key == "dbtable") { 	// Add the schema name to be sure of correct tbl.
			this.dbProp.setProperty(key,
					CD_Schema.SCHEMA.value() + "." + value);
		}else {
			this.dbProp.setProperty(key, value);
		}
	}
	
	public Properties getDbProp() {
		return dbProp;
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	public void getDbProp(Properties dbProp) {
		this.dbProp = dbProp;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}
