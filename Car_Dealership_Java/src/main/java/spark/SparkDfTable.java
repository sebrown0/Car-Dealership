package spark;

import java.sql.SQLException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkDAO;
import database.Database;

/**
 * @author Steve Brown
 * Creates a spark df from a data base table.
 * Use getDataFrame() to access the data frame. 
 */
public class SparkDfTable extends Spark{

	SparkDf dataFrame;
	
	public SparkDfTable(SparkDAO spark, Database mySql) throws Throwable {
		createSparkDf(spark, mySql);
	}

	@Override
	public void createSparkDf(SparkDAO spark, Database db)
			throws SQLException {
		
		dataFrame = new SparkDf();

		Dataset<Row> df = spark.session().read()
				  .format(db.getDbProperty("format")) 
				  .option("url", db.getDbProperty("url"))
				  .option("dbtable", db.getDbProperty("dbtable"))
				  .option("user", db.getDbProperty("username"))
				  .option("password", db.getDbProperty("password"))
				  .load();
//		Dataset<Row> df = spark.session().read()
//				  .format(db.dBPropValue("format")) 
//				  .option("url", db.dBPropValue("url"))
//				  .option("dbtable", db.dBPropValue("dbtable"))
//				  .option("user", db.dBPropValue("username"))
//				  .option("password", db.dBPropValue("password"))
//				  .load();

		dataFrame.setDataFrame(df);		
	
	}
	
	@Override
	public Dataset<Row> getDataFrame() {
		return dataFrame.getDataFrame();
	}


}
