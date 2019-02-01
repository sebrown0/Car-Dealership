package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import dao.SparkDAO;
import database.DataBase;

public class SparkDfCSV implements SparkDAO{
	
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
	public void createNewSparkSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SparkSession session() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dataset<Row> getDataFrame() {
		// TODO Auto-generated method stub
		return dataFrame.getDataFrame();
	}

	@Override
	public void createSparkDf(SparkDAO spark, DataBase db) {
		// TODO Auto-generated method stub
		
	}

	

}
