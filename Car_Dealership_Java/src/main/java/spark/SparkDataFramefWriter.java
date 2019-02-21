/**
 * 
 */
package spark;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.DatabaseDAO;

/**
 * @author Brown
 *
 * Rules for writing a spark df to specified db.
 */
public interface SparkDataFramefWriter {

	boolean writeDfToDbTable(Dataset<Row> df, DatabaseDAO db, String table)		// Write a dataframe to the specified table. 
			throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException; 

	boolean writeDfToDbTable(Dataset<Row> df, DatabaseDAO db) 					// Write a dataframe to the table specified in the DB's properties.
			throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException;
}
