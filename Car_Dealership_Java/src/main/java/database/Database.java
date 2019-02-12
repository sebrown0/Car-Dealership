package database;

import java.sql.Connection;
import java.util.Properties;

import org.apache.spark.sql.SparkSession;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import enums.CD_Schema;
import enums.ErrorCodes;



/**
 * @author Steve Brown Super class that allows data base access.
 */

public class Database implements DatabaseDAO, SparkSessionDAO {

	private Properties dbProp; // Properties needed to connect to the DB.
	private DbConnectionInterface dbConnection; // DB Connection

//	private StoredProcedure dbStoredProcedure;

	public Database() {
		this.dbProp = new Properties();
		this.dbConnection = new DbConnection();

	}
	
	public Connection connection() {
		if (this.dbConnection.connect(this.dbProp) == ErrorCodes.NONE) {
			return dbConnection.connection();
		} else {
			return null;
		}
	}

	// Implementation of methods common to all DBs - Start.
	@Override
	public void setDbProperty(String key, String value) {
		if (key == "dbtable") { // Add the schema name to be sure of correct tbl.
			this.dbProp.setProperty(key, CD_Schema.SCHEMA.value() + "." + value);
		} else {
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

	@Override
	public void setDefaultProperties() {
		// TODO Auto-generated method stub
		System.out.println("public void setDefaultProperties() {");
	}

	@Override
	public DbConnectionInterface dbConnection() {
		// TODO Auto-generated method stub
		return dbConnection;
	}

	@Override
	public StoredProcedure executeSP(String query) {
		StoredProcedure sp = new StoredProcedure(query, dbConnection);
		return sp.execute();

	}

	@Override
	public void dbConnect() {
		if (!this.dbConnection.checkConnection())
			this.dbConnection.connect(dbProp);
	}

	@Override
	public void createNewSparkSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SparkSession session() {
		// TODO Auto-generated method stub
		return null;
	}

}
