package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_objects.RollCall;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *
 *  Return a new task RollCall for a Department. 
 */
public class RollCallInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(TasksDetails tasksDetails, Department tasksDepartment) {
		return new RollCall(tasksDetails, tasksDepartment);
	}
}
