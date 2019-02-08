package database;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import enums.CD_Schema;
import enums.MySqlConn;

/**
 * @author Steve Brown
 * Used mainly to write a data frame to a MySQL DB.
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
	
	// Implementation of methods common to THIS DBs - Start.
	@Override
	public void setDefaultProperties() {
		this.setDbProperty("url", MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty("url_schema", MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty("username", MySqlConn.USERNAME.value());
		this.setDbProperty("password", MySqlConn.PASSWORD.value());
		this.setDbProperty("schema", CD_Schema.SCHEMA.value());
		this.setDbProperty("dbtable", "");				// ++++++++++++ CHANGE THIS +++++++++++++++++++++++++
		this.setDbProperty("format", "jdbc");
		
	}
	
	// Implementation of methods common to THIS DBs - End.

	
	@Override
	// Write the given df to the given table.
	public boolean writeDfToDBTable(Dataset<Row> df, String table)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
	
		this.setDbProperty("dbtable", table);

		df.write()
		.mode(SaveMode.Append)
		.format(this.getDbProperty("format"))
		.option("url", this.getDbProperty("url"))
		.option("dbtable", this.getDbProperty("dbtable"))
		.option("user", this.getDbProperty("username"))
		.option("password", this.getDbProperty("password"))
		.save();

		return true;
	}
	
	@Override
	// Write the given df to the given table.
	// Called with a DB Object which should have the 'dbtable' parameter set to the desired tbl. 
	public boolean writeDfToDBTable(Dataset<Row> df)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		
		df.write()
			.mode(SaveMode.Append)
			.format(this.getDbProperty("format"))
			.option("url", this.getDbProperty("url_schema"))
			.option("dbtable", this.getDbProperty("dbtable"))
			.option("user", this.getDbProperty("username"))
			.option("password", this.getDbProperty("password"))
			.save();

		return true;	
	}

	

	

	// NOT USED AT PRESENT
	// _____________________________________________________________________
//	@Override

//	public void dbConnect() {
//		System.out.println("trying to connect to mysql db");
//		try ( Connection connection = DriverManager.getConnection( 
//				getDbProp().getProperty("url"),
//				getDbProp().getProperty("username"),
//				getDbProp().getProperty("password"))) {
//			setDbConnection(connection);
//		    System.out.println("Database connected!");
//		} catch (SQLException e) {
//		    throw new IllegalStateException("Cannot connect the database!", e);
//		}		
//	}

	
}
