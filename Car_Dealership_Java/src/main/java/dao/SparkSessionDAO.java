package dao;

import org.apache.spark.sql.SparkSession;
/**
 * @author Steve Brown
 *
 *  Interface for Apache Spark. 
 * 
 */

public interface SparkSessionDAO {
	
	// Create a new spark session.
	public void createNewSparkSession();

	// Get the spark session.
	public SparkSession session();
}
