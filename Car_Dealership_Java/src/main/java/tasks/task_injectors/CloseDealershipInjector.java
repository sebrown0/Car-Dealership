package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_objects.CloseDealership;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *  Return a new task CloseDealership.
 */
public class CloseDealershipInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(TasksDetails taskDetails, Department tasksDepartment) {
		return new CloseDealership(taskDetails, tasksDepartment);
	}

}
