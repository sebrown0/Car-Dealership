/**
 * 
 */
package tasks.task_creators;

import departments.department.Department;
import utils.Log;

/**
 * @author Steve Brown
 *
 *	Provides the details for a scheduled task.
 */
public class ScheduledTaskDetails extends AtomicTaskDetails implements ScheduledTasksDetails {

	private Department department;
	private TaskSchedule scheduledTime;

	/**
	 * @param taskType: 		The type of task, i.e. ATOMIC
	 * @param log: 				App log
	 * @param msg:				Message to be logged.
	 * @param objId:			Task's calling object.
	 * @param department:		Department that the task 'belongs' to.
	 * @param scheduledTime:	Task's scheduled start time, duration etc.
	 */
	public ScheduledTaskDetails(TypeOfTask taskType, Log log, String msg, String objId, 
			Department department, TaskSchedule scheduledTime){
		super(taskType, log, msg, objId);
		this.department = department;
		this.scheduledTime = scheduledTime;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.DepartmentTasksDetails#getDepartment()
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

	/*
	 * (non-Javadoc)
	 * @see tasks.ScheduledTasksDetails#getTaskSchedule()
	 */
	@Override
	public TaskSchedule getTaskSchedule() {
		return scheduledTime;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.ScheduledTasksDetails#setTaskSchedule(tasks.TaskSchedule)
	 */
	@Override
	public void setTaskSchedule(TaskSchedule taskSchedule) {
		this.scheduledTime = taskSchedule;
	}

}
