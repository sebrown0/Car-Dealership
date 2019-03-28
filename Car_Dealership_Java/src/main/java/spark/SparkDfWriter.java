/**
 * 
 */
package spark;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import dao.DatabaseDAO;
import enums.DbProperties;

/**
 * @author Steve Brown
 *
 * Writes a dataframe to the database.
 */
public class SparkDfWriter implements SparkDataFramefWriter {

	/*
	 * Write the df to the database using the database properties.
	 */
	private boolean writeDf(Dataset<Row> df, DatabaseDAO db) {
		// TODO - Error checking
		df.write()
		.mode(SaveMode.Append)
		.format(db.getDbProperty(DbProperties.FORMAT.value()))
		.option("url", db.getDbProperty(DbProperties.URL_AND_SCHEMA.value()))
		.option("dbtable", db.getDbProperty(DbProperties.DB_TABLE.value()))
		.option("user", db.getDbProperty(DbProperties.USER_NAME.value()))
		.option("password", db.getDbProperty(DbProperties.PASSWORD.value()))
		.save();
		
		return true;
	}
	
	@Override
	public boolean writeDfToDbTable(Dataset<Row> df, DatabaseDAO db)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		
		return writeDf(df, db);
	}
	
	@Override
	public boolean writeDfToDbTable(Dataset<Row> df, DatabaseDAO db, String table)
			throws SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException {
		
		db.setDbProperty(DbProperties.DB_TABLE.value(), table);
		return writeDf(df, db);
	}

}
