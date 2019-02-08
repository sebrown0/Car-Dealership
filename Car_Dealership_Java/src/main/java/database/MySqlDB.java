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
import enums.DbProperties;
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
		this.setDbProperty(DbProperties.URL.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.URL_AND_SCHEMA.value(), MySqlConn.URL_AND_SCHEMA.value());
		this.setDbProperty(DbProperties.USER_NAME.value(), MySqlConn.USERNAME.value());
		this.setDbProperty(DbProperties.PASSWORD.value(), MySqlConn.PASSWORD.value());
		this.setDbProperty(DbProperties.SCHEMA.value(), CD_Schema.SCHEMA.value());
		//TODO
		this.setDbProperty(DbProperties.DB_TABLE.value(), "");				// ++++++++++++ CHANGE THIS +++++++++++++++++++++++++
		this.setDbProperty(DbProperties.FORMAT.value(), "jdbc");
		System.out.println(this.getDbProperty(DbProperties.PASSWORD.value()));
		
	}
	
	// Implementation of methods common to THIS DBs - End.
	@Override
	// Write the given df to the given table.
	public boolean writeDfToDBTable(Dataset<Row> df, String table)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
	
		this.setDbProperty(DbProperties.DB_TABLE.value(), table);
		df.write()
		.mode(SaveMode.Append)
		.format(this.getDbProperty(DbProperties.FORMAT.value()))
		.option("url", this.getDbProperty(DbProperties.URL_AND_SCHEMA.value()))
		.option("dbtable", this.getDbProperty(DbProperties.DB_TABLE.value()))
		.option("user", this.getDbProperty(DbProperties.USER_NAME.value()))
		.option("password", this.getDbProperty(DbProperties.PASSWORD.value()))
		.save();

		return true;
	}
	
	@Override
	// Write the given df to the given table.
	// Called with a DB Object which should have the 'dbtable' parameter set to the desired tbl. 
	public boolean writeDfToDBTable(Dataset<Row> df)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		//TODO
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
