package database;

import java.sql.Connection;
import java.util.Properties;

import org.apache.spark.sql.SparkSession;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import enums.Schemas;
import enums.ErrorCodes;
import utils.logger.Log;



/**
 * @author Steve Brown 
 * 
 *  Super class that allows data base access.
 */

public class Database implements DatabaseDAO {//, SparkSessionDAO {

	private Properties dbProp; 					// Properties needed to connect to the DB.
	private DbConnectionInterface dbConnection; // DB Connection
	private Log log;
	
	public Database(Log log) {
		this.dbProp = new Properties();
		this.dbConnection = new DbConnection();
		this.log = log;
	}
	
	/*
	 *  Return the the database connection.
	 */
	public Connection connection() {
		if (this.dbConnection.connect(this.dbProp, log) == ErrorCodes.NONE) {
			return dbConnection.connection(log);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#setDbProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void setDbProperty(String key, String value) {
		if (key == "dbtable") { // Add the schema name to be sure of correct tbl.
			this.dbProp.setProperty(key, Schemas.SCHEMA.value() + "." + value);
		} else {
			this.dbProp.setProperty(key, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#getDbProperty(java.lang.String)
	 */
	@Override
	public String getDbProperty(String key) {
		return dbProp.getProperty(key);
	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#getDbProperties()
	 */
	@Override
	public Properties getDbProperties() {
		return dbProp;
	}

	@Override
	public void setProperties(String url, String urlAndSchema, String userName, String password, String dbSchema,
			String format) {
		// Using default.

	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#setDefaultProperties()
	 */
	@Override
	public void setDefaultProperties() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#dbConnection()
	 */
//	@Override
//	public DbConnectionInterface dbConnection() {
////		return null;
//		return dbConnection;
//	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#executeSP(java.lang.String)
	 */
	@Override
	public StoredProcedure executeSP(String query) {
		StoredProcedure sp = new StoredProcedure(query, log);
		return sp.execute();
	}

	/*
	 * (non-Javadoc)
	 * @see dao.DatabaseDAO#dbConnect()
	 */
	@Override
	public void dbConnect() {
		if (!this.dbConnection.checkConnection(log))
			this.dbConnection.connect(dbProp, log);
	}
}
