/**
 * 
 */
package tasks.task_creators;

import departments.department.Department;
import utils.Log;

/**
 * @author Steve Brown
 *
 *	Provides the details for a departmental task.
 */
public class DepartmentTaskDetails extends AtomicTaskDetails implements DepartmentTasksDetails {

	private Department department = null;

	/**
	 * @param taskType: 	The type of task, i.e. ATOMIC
	 * @param log: 			App log
	 * @param msg:			Message to be logged.
	 * @param objId:		Task's calling object.
	 * @param department:	Department that the task 'belongs' to. 	
	 */
	public DepartmentTaskDetails(TypeOfTask taskType, Log log, String msg, String objId, Department department){
		super(taskType, log, msg, objId);
		this.department = department;
	}

	
	/* (non-Javadoc)
	 * @see tasks.TasksDetails#getDepartment()
	 */
	@Override
	public Department getDepartment() {
		return department;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.DepartmentTasksDetails#setDepartment(departments.department.Department)
	 */
	@Override
	public void setDepartment(Department department) {
		this.department = department;
	}

}
