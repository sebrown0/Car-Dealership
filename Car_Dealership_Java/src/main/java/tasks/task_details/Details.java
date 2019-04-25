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

	public Details(String msg, String taskID) {
		this.msg = msg;
		this.taskID = taskID;
	}
	
	public Details(String msg, String taskID, Department tasksDepartment) {
		this.msg = msg;
		this.taskID = taskID;
		this.tasksDepartment = tasksDepartment;
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
}
