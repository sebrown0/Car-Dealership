/**
 * 
 */
package department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import hr_department.EmployeePool;
import hr_department.Team;
import spark.Spark;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public class Department {
	
	private String deptId = "";
	private String deptTask = "";
	private Log log = new Logger(false);
	private Team team = null;
	private SparkSessionDAO spark = null;
	private DatabaseDAO dataBase;
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(String deptId) {
		this.deptId = deptId;
		this.spark = new Spark(deptId, "local", true); 	// TODO - Spark session?
		this.dataBase = new MySqlDB();					// TODO - Database here?
		this.team = new EmployeePool();
	}
		
	public DatabaseDAO dataBase() {
		return dataBase;
	}
	
	public SparkSessionDAO spark() {
		return spark;
	}

	public Team team() {
		return team;
	}
	
	public void team(Team t) {
		this.team = t;
	}
	
	public Log log() {
		return log;
	}

	public void log(Log log) {
		this.log = log;
	}

	public String getDeptId() {
		return deptId;
	}
	
	public String getDeptId(String childId) {
		return (deptId + childId);
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptTask() {
		return deptTask;
	}

	public void setDeptTask(String deptTask) {
		this.deptTask = deptTask;
	}
	
}
