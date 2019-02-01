package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import dao.SparkDAO;
import database.DataBase;
import enums.CD_Schema;
import enums.MySqlConn;

public class SparkDfTable implements SparkDAO{

	SparkDf dataFrame;
	
	@Override
	public void createSparkDf(SparkDAO spark, DataBase db) {
		dataFrame = new SparkDf();
		
		Dataset<Row> df = spark.session().read()
				  .format(db.dBPropValue("format")) //jdbc
				  .option("url", db.dBPropValue("url"))
				  .option("dbtable", db.dBPropValue("dbtable"))
				  .option("user", db.dBPropValue("username"))
				  .option("password", db.dBPropValue("password"))
				  .load();

		dataFrame.setDataFrame(df);		
	}
	
	@Override
	public void createSparkDf(SparkDAO spark, String table) {

		System.out.println("!!!!!!!!!!!!!!!!! CREATING DF WITH TABLE");
		
//		MySqlDB mySql = new MySqlDB(
//				//MySqlConn.URL.value(),
//				MySqlConn.USERNAME.value(), 
//				MySqlConn.PASSWORD.value());
		
//		dataFrame = new SparkDf();
//		
//		Dataset<Row> df = spark.session().read()
//				  .jdbc(MySqlConn.URL.value(),
//						  CD_Schema.SCHEMA.value() + "." + table, 
//						  MySqlConn.USERNAME.value(),MySqlConn.PASSWORD.value());
//
//
//		dataFrame.setDataFrame(df);
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

	

	
	

}
