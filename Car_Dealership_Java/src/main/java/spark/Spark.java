package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import dao.SparkDAO;
import database.DataBase;


 /**
 * @author Steve Brown
 * Creates a spark session.
 * Use getSparkSession() to access the session/object. 
 */
public class Spark implements SparkDAO{

	private SparkSession spark;
	private String appName;
	private String master;
	private boolean activeSession = false;

	public Spark(String appName, String master, boolean createSession) {
		super();
		this.appName = appName;
		this.master = master;

		if (createSession) {
			createNewSparkSession();
		}
	}

	public void createNewSparkSession() {
		// We only want 1 session per instance
		if (!activeSession) {
			spark = SparkSession.builder().appName("appName").master(master).getOrCreate();
			activeSession = true;
//			System.out.println("Spark session:  " + appName + " created.");
		} else {
			System.out.println("Spark session:  " + appName + " already running!");
		}

	}

	@Override
	public SparkSession session() {
		return spark;
	}
	
	@Override
	public void createSparkDf(SparkDAO sparkSession, String path) {
		// TODO Auto-generated method stub
	}

	public String getAppName() {
		return appName;
	}

	public String getMaster() {
		return master;
	}

	@Override
	public Dataset<Row> getDataFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSparkDf(SparkDAO spark, DataBase db) {
		// TODO Auto-generated method stub
		
	}


}
