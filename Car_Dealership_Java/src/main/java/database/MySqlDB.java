package database;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import dao.DB_DAO;
import dao.SparkDAO;
import enums.CD_Schema;
import enums.MySqlConn;
import enums.TableNames;

/**
 * @author Steve Brown
 * Used mainly to write a data frame.
 */
public class MySqlDB extends DataBase{

	public MySqlDB(String url, String urlSchema, String user, String password, String dbSchema, String tblName,
			String format) {
		super(url, urlSchema, user, password, dbSchema, tblName, format);
		// USING THE DEFAULT MySQL CONNECTION (POSTGRES NOT IMPLEMENTED YET).
	}

	// Setup default connection properties for MySql with JDBC
	public MySqlDB(String tblName) {
		super();
		this.dBPropValue("url", MySqlConn.URL_AND_SCHEMA.value()); 
		this.dBPropValue("url_schema", MySqlConn.URL_AND_SCHEMA.value());
		this.dBPropValue("username", MySqlConn.USERNAME.value());
		this.dBPropValue("password", MySqlConn.PASSWORD.value());
		this.dBPropValue("schema", CD_Schema.SCHEMA.value());
		this.dBPropValue("dbtable", tblName);
		this.dBPropValue("format", "jdbc");
		
	}
	

	@Override
	// Write the given df to the given table.
	public boolean writeDfToDBTable(Dataset<Row> df, String table)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
	
		this.dBPropValue("dbtable", table);
		
		df.write()
			.mode(SaveMode.Append)
			.format(this.dBPropValue("format"))
			.option("url", this.dBPropValue("url"))
			.option("dbtable", this.dBPropValue("dbtable"))
			.option("user", this.dBPropValue("username"))
			.option("password", this.dBPropValue("password"))
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
			.format(this.dBPropValue("format"))
			.option("url", this.dBPropValue("url_schema"))
			.option("dbtable", this.dBPropValue("dbtable"))
			.option("user", this.dBPropValue("username"))
			.option("password", this.dBPropValue("password"))
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
