package spark;

import org.apache.spark.sql.SparkSession;

import dao.SparkSessionDAO;
import utils.Log;
import utils.Loggable;


 /**
 * @author Steve Brown
 * 
 * Creates a spark session.
 * Use getSparkSession() to access the session/object. 
 */
public class Spark implements SparkSessionDAO, Loggable {

	private SparkSession spark;
	private String appName;
	private String master;
	private boolean activeSession = false;
	private Log log;
	
	public Spark(String appName, String master, boolean createSession, Log log) {
		super();
		this.appName = appName;
		this.master = master;
		this.log = log;

		if (createSession)
			createNewSparkSession();
	}

	@Override
	public void createNewSparkSession() {
		// We only want 1 session per instance
		if (!activeSession) {
			spark = SparkSession.builder().appName("appName").master(master).getOrCreate();
			activeSession = true;
			log.logEntry(this, "Spark session:  (" + appName + ") created.");			
		} else {
			log.logEntry(this, "Spark session:  (" + appName + ") already running!");
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
