package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dao.DBProperty;
import dao.DatabaseDAO;
import enums.Schemas;
import utils.logger.Log;



/**
 * @author Steve Brown 
 * 
 *  Super class for data base access.
 */

public abstract class Database implements DatabaseDAO, DBProperty {

	private Properties dbProp; 					
	private Log log;
		
	public Database(Log log) {
		this.dbProp = new Properties();
		this.log = log; 
	}
		
	@Override
	public Connection getDbConnection() {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace(); // TODO - Handle this error.
		}
		return conn;
	}
	
	@Override
	public void closeConnection(Connection c) {
		new Thread(() -> {
			try {
				Thread.sleep(100);
				c.close();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}).start();
	}
	
	@Override
	public void setDbProperty(String key, String value) {
		if (key == "dbtable") // Add the schema name to be sure of correct tbl.
			this.dbProp.setProperty(key, Schemas.SCHEMA.value() + "." + value);
		else 
			this.dbProp.setProperty(key, value);
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
	public StoredProcedure executeSP(String query) {
		Connection c = getDbConnection();
		StoredProcedure sp = new StoredProcedure(query, log, c);
		sp.execute();
		closeConnection(c);
		return sp;
	}
}
