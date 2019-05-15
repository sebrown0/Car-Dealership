
package spark;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;

/**
 * @author Steve Brown
 * 
 * Reads from a specified source into a spark data frame.
 * Use getDataFrame() to access the data frame. 
 */
public class SparkDfReader implements SparkDfReadInterface{

	private SparkSessionDAO spark;
	private Dataset<Row> df;
		
	public SparkDfReader(SparkSessionDAO spark) {
		this.spark = spark;
	}

	@Override
	public void readFile(String path, String format) {
		this.df =  spark.session().read().format(format)
				.option("multiline", true)
				.option("inferSchema",false)
				.load(path);		
	}

	@Override
	public void readTable(SparkSessionDAO spark, DatabaseDAO db) throws SQLException {
		this.df = spark.session().read()
			  .format(db.getDbProperty("format")) 
			  .option("url", db.getDbProperty("url"))
			  .option("dbtable", db.getDbProperty("dbtable"))
			  .option("user", db.getDbProperty("username"))
			  .option("password", db.getDbProperty("password"))
			  .load();
	}

	@Override
	public Dataset<Row> getDataFrame() {
		return df;
	}
}
