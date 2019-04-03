/**
 * 
 */
package departments.department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import department_tasks.DepartmentTask;
import department_tasks.Task_NOB;
import departments.hr_department.Staff;
import employees.Employee;
import task_scheduler.Manager;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import timer.Timers;
import utils.Log;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public abstract class Department implements DepartmentTask {
//public abstract class Department {
//	protected Messenger deptMessanger = new DepartmentMessenger();	
//	private TaskProducer  taskProducer = null;	
//	private TaskConsumer_NOT_USED taskConsumer = null;
//	private BlockingQueue<Task> departmentTaskList = new ArrayBlockingQueue<Task>(15); // TODO - Queue size.
//	private Log log = new Logger(false);
//	private SparkSessionDAO spark = new Spark(deptId, "local", true); 	// TODO - Spark session?
//	private DatabaseDAO dataBase  = new MySqlDB();						// TODO - Database here?;	
	private String deptId = "";
	protected String objId = "";	// TODO - BOTH???????
	private String deptName = "";
	private Staff idleStaff = new Staff();
	private Staff workingStaff = new Staff();
	
	protected Messenger deptMessanger = null;
	protected Employee workingEmployee = null;			// List
//	protected Task_NOB currentTask = null;    				// List
	protected Log log;
	
	private SparkSessionDAO spark;
	private DatabaseDAO database;

	private Timers timer;
	private Manager taskManager;
	
	public Department(String deptId, String deptName, DealerDAO dealerDAO) {
		this.objId = "<" + deptName + " "+ deptId + ">";
		this.deptId = deptId;
		this.deptName = deptName;
		this.log = dealerDAO.getLog();
		this.timer = dealerDAO.getTimer();
		this.taskManager = dealerDAO.getTaskManager();
		this.database = dealerDAO.getDatabase();
		this.spark = dealerDAO.getSpark();
	}
		
	public void departmentTask(TaskConsumer task) {
		taskManager.manageTask(task);
	}
		
	public void receiveTask(Task_NOB task) {
		log.logEntry(getObjId(), "New task received");

		task.setDepartment(this);
//		this.currentTask = task;				
//		currentTask.setDepartment(this);
		delegateTask(task);
	}
	

	public void receiveTask(Task task, Employee employee) {
		log.logEntry(getObjId(), "New task received");

//		this.currentTask = task;				
//		currentTask.setDepartment(this);
//		delegateTask(task, employee);
	}
	
	abstract public void delegateTask(Task_NOB task); 
	abstract public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role);
	
	public void delegateTask(Task_NOB task, Employee employee) {
		employee.addTask(task);		
		this.workingStaff.addDepStaffMember(employee, log);
	}
	
	public Employee working() {
		return workingEmployee;
	}
		
	public void assignMessenger(Messenger messenger) {
		this.deptMessanger = messenger;
	}
	
	public void newDeptMember(long empId, String firstName, String lastName, String deptId, String role) {
		this.addDeptStaffMember(empId, firstName, lastName, deptId, role);
	}	
	
	/*
	 *  Getters and Setters below.
	 */
	public Messenger getMessanger() {
		return deptMessanger;
	}
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public Timers timer() {
		return timer;
	}
	
	public DatabaseDAO database() {
		return this.database;
	}

	public Staff idleStaff() {
		return this.idleStaff;
	}
	
	public Staff workingStaff() {
		return this.workingStaff;
	}
	
	public SparkSessionDAO spark() {
		return spark;
	}
	
	public Log log() {
		return log;
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
