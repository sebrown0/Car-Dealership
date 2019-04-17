/**
 * 
 */
package tasks.task_details;

import departments.department.Department;

/**
 * @author Steve Brown
 *
 *	Provides the details for a specific task.
 */
public class Details implements TasksDetails {

//	private TypeOfTask taskType;
//	private Log log;
//	private DealerDAO dealerDAO;
	private String msg = "None";
	private String taskID = "";
	private Department tasksDepartment = null;
	private TaskSchedule tasksSchedule = null;

	public Details(String msg, String taskID) {
		this.msg = msg;
		this.taskID = taskID;
	}
	
	public Details(String msg, String taskID, Department tasksDepartment) {
		this.msg = msg;
		this.taskID = taskID;
		this.tasksDepartment = tasksDepartment;
	}
	
	public Details(String msg, String taskID, Department tasksDepartment, TaskSchedule tasksSchedule) {
		this.msg = msg;
		this.taskID = taskID;
		this.tasksDepartment = tasksDepartment;
		this.tasksSchedule = tasksSchedule;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public String getTaskID() {
		return taskID;
	}

	@Override
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Override
	public Department getDepartment() {
		return tasksDepartment;
	}

	@Override
	public void setDepartment(Department department) {
		this.tasksDepartment = department;
	}

	@Override
	public TaskSchedule getTasksSchedule() {
		return tasksSchedule;
	}

	@Override
	public void setTasksSchedule(TaskSchedule taskSchedule) {
		this.tasksSchedule = taskSchedule;		
	}
}
