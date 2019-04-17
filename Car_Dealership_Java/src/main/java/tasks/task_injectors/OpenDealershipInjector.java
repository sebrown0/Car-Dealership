package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_objects.OpenDealership;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *  Return a new task OpenDealership for NO Department. 
 *  To be executed immediately.
 */
public class OpenDealershipInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(TasksDetails taskDetails, Department tasksDepartment) {
		return new OpenDealership(taskDetails, tasksDepartment);
	}
}
