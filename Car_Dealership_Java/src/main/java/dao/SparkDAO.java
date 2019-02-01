package dao;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import database.DataBase;
import database.MySqlDB;
/**
 * @author Steve Brown
 * Interface for Apache Spark. 
 * 
 */

public interface SparkDAO {
	public void createNewSparkSession();
	public void createSparkDf(String table); 					// Create df using the table name given.
	public void createSparkDf(SparkDAO spark, String table); 	// Create df using the table name given.
	public void createSparkDf(SparkDAO spark, DataBase db);		// Create df using from the DB given.
	public SparkSession session();								// Get the spark session.
	public Dataset<Row> getDataFrame();							// Get a created df.
}
