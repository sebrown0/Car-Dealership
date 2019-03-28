/**
 * 
 */
package department;

import java.util.concurrent.BlockingQueue;

import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import department_tasks.DepartmentTask;
import department_tasks.Task;
import department_tasks.TaskConsumer;
import department_tasks.TaskProducer;
import employees.Employee;
import hr_department.Staff;
import spark.Spark;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 * Super class for all departments
 */
public abstract class Department implements DepartmentTask {
//public abstract class Department {
	
	protected String objId = "";
//	protected Messenger deptMessanger = new DepartmentMessenger();
	protected Messenger deptMessanger = null;
	
	private TaskProducer  taskProducer = null;	
	private TaskConsumer taskConsumer = null;
	
	private String deptId = "";
	private String deptName = "";
	private Staff idleStaff = new Staff();
	private Staff workingStaff = new Staff();
//	private BlockingQueue<Task> departmentTaskList = new ArrayBlockingQueue<Task>(15); // TODO - Queue size.
	private Log log = new Logger(false);
	private SparkSessionDAO spark = new Spark(deptId, "local", true); 	// TODO - Spark session?
	private DatabaseDAO dataBase  = new MySqlDB();						// TODO - Database here?;
	
	protected Employee workingEmployee = null;			// List
	protected Task currentTask = null;    				// List
	
	public Department(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.objId = "<" + deptName + " "+ deptId + ">";
//		this.taskProducer = new TaskProducer(taskQueue);
//		this.taskConsumer = new TaskConsumer(taskQueue);
		
		// TODO - Test
//		Thread threadConsumer = new Thread(taskConsumer);
//		threadConsumer.start();
	}
	
	// NEWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
	
	@Override
	public void receiveTask(Department department, Task task) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void receiveTask(Department department, Task task, Employee employee) {
		// TODO Auto-generated method stub
		
	}
	
	public void receiveTask(Task task) {
		log.logEntry(getObjId(), "New task received");

		task.setDepartment(this);
//		this.currentTask = task;				
//		currentTask.setDepartment(this);
		delegateTask(task);
	}
	

	public void receiveTask(Task task, Employee employee) {
		log.logEntry(getObjId(), "New task received");

		this.currentTask = task;				
		currentTask.setDepartment(this);
		delegateTask(task, employee);
	}
	
	abstract public void delegateTask(Task task); 
	
	public void delegateTask(Task task, Employee employee) {
		employee.addTask(task);		
		this.workingStaff.addDepStaffMember(employee);
	}
	
	public Employee working() {
		return workingEmployee;
	}
	// NEWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
	
	public void assignMessenger(Messenger messenger) {
		this.deptMessanger = messenger;
	}
	
	public Messenger getMessanger() {
		return deptMessanger;
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

	public Staff idleStaff() {
		return this.idleStaff;
	}
	
	public Staff workingStaff() {
		return this.workingStaff;
	}


//	public BlockingQueue<Task> getTaskList() {
//		return departmentTaskList;
//	}
	
//	public void addTask(Task_OLD task) {
//		log.logEntry(objId, "Adding task <" + task.getClass().getSimpleName() + "> to task list");
//		
//		taskProducer.addTask(task);
//		Thread threadProducer = new Thread(taskProducer);
//		
////		Thread threadConsumer = new Thread(new TaskConsumer(taskQueue));
////		
//		threadProducer.start();
//		try {
//			threadProducer.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		taskConsumer.start();
//		
////		try {
////			this.taskQueue.put(task);
////		} catch (InterruptedException e) {
////			e.printStackTrace();										// TODO Error handler
////		}		
//	}

//	public Task_OLD getTask() {
//		Task_OLD task = null;
//		
//		if(!taskQueue.isEmpty()) {
//			try {
//				task = this.taskQueue.take();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		return task;
//	}	
	
	public void newDeptMember(long empId, String firstName, String lastName, String deptId, String role) {
		this.addDeptStaffMember(empId, firstName, lastName, deptId, role);
	}
	
	abstract public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role);
	
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
