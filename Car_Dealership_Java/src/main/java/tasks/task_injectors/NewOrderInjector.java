package tasks.task_injectors;

import departments.department.Department;
import tasks.task_objects.NewOrder;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *  Return a new task - NewOrder. 
 *  To be executed immediately.
 */
public class NewOrderInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(Department tasksDepartment) {
		return new NewOrder(tasksDepartment);
	}
}
