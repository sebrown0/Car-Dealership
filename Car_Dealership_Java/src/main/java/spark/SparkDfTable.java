package spark;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkSessionDAO;
import database.Database;

/**
 * @author Steve Brown
 * Creates a spark df from a data base table.
 * Use getDataFrame() to access the data frame. 
 */
public class SparkDfTable extends SparkDataframe{

//	SparkDataframe dataFrame;
	
	public SparkDfTable(SparkSessionDAO spark, Database db) throws Throwable {
		createSparkDf(spark, db);
	}

	@Override
	public void createSparkDf(SparkSessionDAO spark, Database db)
			throws SQLException {
		
//		dataFrame = new SparkDataframe();

		Dataset<Row> df = spark.session().read()
				  .format(db.getDbProperty("format")) 
				  .option("url", db.getDbProperty("url"))
				  .option("dbtable", db.getDbProperty("dbtable"))
				  .option("user", db.getDbProperty("username"))
				  .option("password", db.getDbProperty("password"))
				  .load();

//		dataFrame.setDataFrame(df);		
		this.setDataFrame(df);
	
	}
	
	@Override
	public Dataset<Row> getDataFrame() {
		return this.getDataFrame();
//		return dataFrame.getDataFrame();
	}


}
