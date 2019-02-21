/**
 * 
 */
package department;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import department_tasks.Task;
import department_tasks.TaskConsumer;
import department_tasks.TaskProducer;
import hr_department.Staff;
import spark.Spark;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public class Department {
	
	protected String objId = "";
	private String deptId = "";
	private String deptName = "";
	private Staff staff = new Staff();
	private BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<Task>(15); // TODO - Queue size.
	private Log log = new Logger(false);
	private SparkSessionDAO spark = new Spark(deptId, "local", true); 	// TODO - Spark session?
	private DatabaseDAO dataBase  = new MySqlDB();						// TODO - Database here?;
	
	public Department(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.objId = "<" + deptName + " "+ deptId + ">";
	}


	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public DatabaseDAO database() {
		return this.dataBase;
	}

	public Staff staff() {
		return this.staff;
	}


	public BlockingQueue<Task> getTaskList() {
		return taskQueue;
	}
	
	public void addTask(Task task) {
		log.logEntry(objId, "Adding task <" + task.getClass().getSimpleName() + "> to task list");
		
		Thread taskProducer = new Thread(new TaskProducer(taskQueue, task));
		Thread taskConsumer = new Thread(new TaskConsumer(taskQueue));
		
		taskProducer.start();
		taskConsumer.start();
		
//		try {
//			this.taskQueue.put(task);
//		} catch (InterruptedException e) {
//			e.printStackTrace();										// TODO Error handler
//		}		
	}

	public Task getTask() {
		Task task = null;
		
		if(!taskQueue.isEmpty()) {
			try {
				task = this.taskQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return task;
	}	
	
	public void newDeptMember(long empId, String firstName, String lastName, String deptId, String role) {
		this.addDeptStaffMember(empId, firstName, lastName, deptId, role);
	}
	
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {
		
	}
	
	public DatabaseDAO dataBase() {
		return dataBase;
	}
	
	public SparkSessionDAO spark() {
		return spark;
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

	public String deptName() {
		return deptName;
	}	
}
