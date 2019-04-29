/**
 * 
 */
package departments.department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import departments.hr_department.DepartmentStaff;
import employees.Employee;
import employees.EmployeeDetails;
import task_scheduler.Manager;
import task_scheduler.TaskReceiver;
import tasks.task_super_objects.AtomicTask;
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
	
	private DepartmentDetails deptDetails;
	private DepartmentStaff idleStaff = new DepartmentStaff();
	private DepartmentStaff workingStaff = new DepartmentStaff();
	
	protected Messenger deptMessanger = null;
	protected Employee workingEmployee = null;			// List
	
	protected Log log;
	private SparkSessionDAO sparkSession;
	private DatabaseDAO database;
	protected Timer timer;
	private Manager taskManager;
	
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
		employee.addTask(task);		
		this.workingStaff.addDepStaffMember(employee, log);
	}
	
	public Employee working() {
		return workingEmployee;
	}
		
	public void assignMessenger(Messenger messenger) {
		this.deptMessanger = messenger;
	}
	
	public void newDeptMember(EmployeeDetails employeeDetails) {
		this.addDeptStaffMember(employeeDetails);
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
		return this.database;
	}

	public DepartmentStaff idleStaff() {
		return this.idleStaff;
	}
	
	public DepartmentStaff workingStaff() {
		return this.workingStaff;
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