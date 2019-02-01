package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import dao.SparkDAO;
import database.DataBase;

public class SparkDfJSON implements SparkDAO {

	SparkDf dataFrame;
	
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
	public SparkSession session() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewSparkSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dataset<Row> getDataFrame() {
		return dataFrame.getDataFrame();
	}

	@Override
	public void createSparkDf(SparkDAO spark, DataBase db) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Dataset<Row> getDataFrame() {
//		// TODO Auto-generated method stub
//		return this.getDataFrame();
//	}

	

}
