package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;
import tasks.concrete.NewOrder;

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
