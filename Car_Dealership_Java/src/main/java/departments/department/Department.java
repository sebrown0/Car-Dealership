/**
 * 
 */
package departments.department;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import departments.hr_department.DepartmentStaff;
import people.employees.DepartmentManager;
import people.employees.Employee;
import task_scheduler.DepartmentTaskReceiver;
import tasks.task_super_objects.Task;
import timer.Timer;
import utils.logger.Log;
import utils.logger.Loggable;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public abstract class Department implements Loggable, DepartmentTaskReceiver{
	
	private SparkSessionDAO sparkSession;
	private DatabaseDAO database;
	private DepartmentDetails deptDetails;
	private DepartmentManager deptManager;
	private DealerDAO dealerDAO;
	
	protected DepartmentStaff idleStaff = new DepartmentStaff();
	protected DepartmentStaff workingStaff = new DepartmentStaff();
	protected Messenger deptMessanger = null;
	protected Log log;
	protected Timer timer;
	
	abstract public void delegateTask(Task task); 
	abstract public void addDeptStaffMember(EmployeeDetails employeeDetails);
	
	public void setDepartmentDetails(DepartmentDetails details) {
		this.deptDetails = details;
	}
	
	public void setDepartmentDAO(DealerDAO dealerDAO) {
		this.dealerDAO = dealerDAO;
		this.log = dealerDAO.getLog();
		this.timer = dealerDAO.getTimer();
		this.database = dealerDAO.getDatabase();
		this.sparkSession = dealerDAO.getSpark();
	}
	

	@Override
	public <T extends Task> void accept(T t) {		
	}	
	
	public DealerDAO getDealerDAO() {
		return dealerDAO;
	}
	
	public String getDeptID() {
		return deptDetails.getDeptID();
	}
	
	public String getDeptName() {
		return deptDetails.getDeptName();
	}
	
	public boolean hasAnAvailableEmployee() {
		return idleStaff.staffListNotEmpty();
	}
	
	public Employee nextAvailableEmployee() {
		return idleStaff.nextEmployee();
	}
	
	public void addEmployeeToWorkingList(Employee emp) {
		idleStaff.removeEmployee(emp.getID());
		workingStaff.addDepStaffMember(emp);
	}
	
	public void addEmployeeToIdleList(Employee emp) {
		workingStaff.removeEmployee(emp.getID());
		idleStaff.addDepStaffMember(emp);
	}
	
	public DepartmentManager getDeptManager() {
		return deptManager;
	}
	
	public boolean deptHasManager() {
		return (deptManager != null) ? true : false;
	}
	
	public void assignTaskToDeptManager(Task task) {
		deptManager.delegateTask(task);
	}
	
	public void setDeptManager(EmployeeDetails deptManager) {
		if(this.deptManager == null) {
			log.logEntry(this, getDeptName() + " Manager is " + deptManager.getFullName());
			this.deptManager = new DepartmentManager(deptManager, this);
		}else {
//			log.logEntry(this, getDeptName() + " Already has a manager.");
		}
	}

	public DepartmentDetails getDepartmentDetails() {
		return deptDetails;
	}
		
	public int currentTime() {
		return timer.currentTime();
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

	public SparkSessionDAO spark() {
		return sparkSession;
	}
	
	public Log log() {
		return log;
	}
}