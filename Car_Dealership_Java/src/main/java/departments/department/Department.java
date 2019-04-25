/**
 * 
 */
package departments.department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import departments.hr_department.DepartmentStaff;
import employees.Employee;
import task_scheduler.Manager;
import task_scheduler.TaskReceiver;
import tasks.task_super_objects.AtomicTask;
import tasks.task_super_objects.Task;
import timer.Timers;
import utils.Log;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public abstract  class Department implements TaskReceiver{
	
	private String deptId = "";
	private String deptName = "";
	private DepartmentStaff idleStaff = new DepartmentStaff();
	private DepartmentStaff workingStaff = new DepartmentStaff();
	
	protected String objId = "";	// TODO - BOTH???????
	protected Messenger deptMessanger = null;
	protected Employee workingEmployee = null;			// List
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
		
//	@Override
//	public void giveTask(Task t) {
//		t.setTasksDepartment(this);;
//		taskManager.accept(t);
//	}
	
	public void departmentTask(AtomicTask task) {
//		taskManager.manageTask(task, null); // TODO - INSERT EMPLOYEE!!!!!!!!!
	}
		
	public void receiveTask(Task task) {
		log.logEntry(getObjId(), "New task received");

//		task.setDepartment(this);
//		this.currentTask = task;				
//		currentTask.setDepartment(this);
//		delegateTask(task);
	}
	

	public void receiveTask(Task task, Employee employee) {
		log.logEntry(getObjId(), "New task received");

//		this.currentTask = task;				
//		currentTask.setDepartment(this);
//		delegateTask(task, employee);
	}
	
	abstract public void delegateTask(Task task); 
	abstract public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role);
	
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

	public DepartmentStaff idleStaff() {
		return this.idleStaff;
	}
	
	public DepartmentStaff workingStaff() {
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
