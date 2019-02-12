package dao;

import org.apache.spark.sql.SparkSession;
/**
 * @author Steve Brown
 * Interface for Apache Spark. 
 * 
 */

public interface SparkSessionDAO {
	public void createNewSparkSession();
//	public void createSparkDf(String table); 					// Create df using the table name given.
//	public void createSparkDf(SparkSessionDAO spark, String table); 	// Create df using the table name given.
//	public void createSparkDf(SparkSessionDAO spark, Database db)		// Create df using from the DB given.
//			throws SQLException;
	
	public SparkSession session();								// Get the spark session.
//	public Dataset<Row> getDataFrame();							// Get a created df.
	
		
//	boolean writeDfToDBTable(Dataset<Row> df, String table)		// Write a dataframe to the specified table. 
//				throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException;
//		
//	boolean writeDfToDBTable(Dataset<Row> df) 					// Write a dataframe to the table specified in the DB's properties.
//				throws  SQLException, BatchUpdateException, SQLIntegrityConstraintViolationException;
}
