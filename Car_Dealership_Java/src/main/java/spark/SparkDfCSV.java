package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkDAO;
/**
 * @author Steve Brown
 * Creates a spark df from a CSV file.
 * Use getDataFrame() to access the data frame. 
 */
public class SparkDfCSV extends Spark{
	
	SparkDf dataFrame;

	public void createSparkDf(SparkDAO spark, String path) {
		
		dataFrame = new SparkDf();
		Dataset<Row> df = spark.session().read().format("csv")
			.option("header", "true")
			.option("multiline", true)
			.option("inferSchema",false)
			.load(path);
		
		dataFrame.setDataFrame(df);
	}

	@Override
	public Dataset<Row> getDataFrame() {
		// TODO Auto-generated method stub
		return dataFrame.getDataFrame();
	}

}
