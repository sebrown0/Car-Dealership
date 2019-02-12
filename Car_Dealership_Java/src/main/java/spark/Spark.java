package spark;

import org.apache.spark.sql.SparkSession;

import dao.SparkSessionDAO;


 /**
 * @author Steve Brown
 * 
 * Creates a spark session.
 * Use getSparkSession() to access the session/object. 
 */
public class Spark implements SparkSessionDAO{

	private SparkSession spark;
	private String appName;
	private String master;
	private boolean activeSession = false;

	public Spark() {
		// TODO Auto-generated constructor stub
	}

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
			System.out.println("Spark session:  " + appName + " created.");			// TODO - logger
		} else {
			System.out.println("Spark session:  " + appName + " already running!");
		}

	}

	public String getAppName() {
		return appName;
	}

	public String getMaster() {
		return master;
	}

	@Override
	public SparkSession session() {
		return spark;
	}

}
