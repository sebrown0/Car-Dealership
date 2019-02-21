package spark;

import org.apache.spark.sql.SparkSession;

import dao.SparkSessionDAO;
import utils.Log;
import utils.Logger;


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

	private Log log = new Logger(false);
	private static final String objId = "<Spark>";
	
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
			log.logEntry(objId, "Spark session:  (" + appName + ") created.");			
		} else {
			log.logEntry(objId, "Spark session:  (" + appName + ") already running!");
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
