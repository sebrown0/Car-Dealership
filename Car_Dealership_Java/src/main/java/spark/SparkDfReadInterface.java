/**
 * 
 */
package spark;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkSessionDAO;
import database.Database;

/**
 * @author Steve Brown
 *
 * Rules for reading a data source and converting it into a spark data frame.
 */
public interface SparkDfReadInterface {
	
	public void readFile(String path, String format); 				// Read Spark supported file format into a df, i.e. JSON.

	public void readTable(SparkSessionDAO spark, Database db)		// Create df using properties from the DB.
			throws SQLException;

	public Dataset<Row> getDataFrame();								// Get the result of any of the above.
	

}
