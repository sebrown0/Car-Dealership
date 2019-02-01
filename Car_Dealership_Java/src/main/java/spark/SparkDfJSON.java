
package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkDAO;

/**
 * @author Steve Brown
 * Creates a spark df from a JSON file.
 * Use getDataFrame() to access the data frame. 
 */
public class SparkDfJSON extends Spark {

	SparkDf dataFrame;
	
	public SparkDfJSON(String appName, String master, boolean createSession) {
		super(appName, master, createSession);
		// TODO Auto-generated constructor stub
	}
	
	
	public SparkDfJSON(SparkDAO spark, String path) {
		super();
		createSparkDf(spark, path);	// Create the df at time of construction.
	}

	@Override
	public void createSparkDf(SparkDAO spark, String path) {

		dataFrame = new SparkDf();
		Dataset<Row> df =  spark.session().read().format("json")
				.option("multiline", true)
				.option("inferSchema",false)
				.load(path);
		
		dataFrame.setDataFrame(df);
	}
	
	@Override
	public Dataset<Row> getDataFrame() {
		return dataFrame.getDataFrame();
	}


	

}
