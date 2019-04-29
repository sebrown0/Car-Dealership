/**
 * 
 */
package departments.department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import departments.hr_department.DepartmentStaff;
import people.employees.Employee;
import task_scheduler.Manager;
import task_scheduler.TaskReceiver;
import tasks.task_super_objects.Task;
import timer.Timer;
import utils.Log;
import utils.Loggable;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public abstract class Department implements Loggable, TaskReceiver{
	
	private DepartmentStaff idleStaff = new DepartmentStaff();
	private DepartmentStaff workingStaff = new DepartmentStaff();
	private DepartmentDetails deptDetails;
	private SparkSessionDAO sparkSession;
	private DatabaseDAO database;
	private Manager taskManager;
	
	protected Messenger deptMessanger = null;
	protected Log log;
	protected Timer timer;
		
	public Department(DepartmentDetails deptDetails, DealerDAO dealerDAO) {
		this.deptDetails = deptDetails;
		this.log = dealerDAO.getLog();
		this.timer = dealerDAO.getTimer();
		this.taskManager = dealerDAO.getTaskManager();
		this.database = dealerDAO.getDatabase();
		this.sparkSession = dealerDAO.getSpark();
	}

	abstract public void delegateTask(Task task); 
	abstract public void addDeptStaffMember(EmployeeDetails employeeDetails);
	
	public void delegateTask(Task task, Employee employee) {
//		employee.addTask(task);		
//		this.workingStaff.addDepStaffMember(employee, log);
	}
	
	public void assignMessenger(Messenger messenger) {
		this.deptMessanger = messenger;
	}
		
	public DepartmentDetails departmentDetails() {
		return deptDetails;
	}
	
	public Messenger getMessanger() {
		return deptMessanger;
	}
	
	public Timer timer() {
		return timer;
	}
	
	public DatabaseDAO database() {
		return database;
	}

	public DepartmentStaff idleStaff() {
		return idleStaff;
	}
	
	public DepartmentStaff workingStaff() {
		return workingStaff;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}

	public void setSparkSession(SparkSessionDAO sparkSession) {
		this.sparkSession = sparkSession;
	}

	public void setDatabase(DatabaseDAO database) {
		this.database = database;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setTaskManager(Manager taskManager) {
		this.taskManager = taskManager;
	}

	public SparkSessionDAO spark() {
		return sparkSession;
	}
	
	public Log log() {
		return log;
	}
}


//@Override
//public void giveTask(Task t) {
//	t.setTasksDepartment(this);;
//	taskManager.accept(t);
//}

//public void departmentTask(AtomicTask task) {
//	taskManager.manageTask(task, null); // TODO - INSERT EMPLOYEE!!!!!!!!!
//}
//public void receiveTask(Task task) {
////	log.logEntry(getObjId(), "New task received");
//
////	task.setDepartment(this);
////	this.currentTask = task;				
////	currentTask.setDepartment(this);
////	delegateTask(task);
//}
//
//
//public void receiveTask(Task task, Employee employee) {
////	log.logEntry(getObjId(), "New task received");
//
////	this.currentTask = task;				
////	currentTask.setDepartment(this);
////	delegateTask(task, employee);
//}