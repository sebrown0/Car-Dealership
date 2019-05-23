package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;
import tasks.concrete.MeetCustomer;

/**
 * @author Steve Brown
 *
 *  Return a new task MeetCustomer. 
 *  To be executed immediately.
 */
public class MeetCustomerInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(Department tasksDepartment) {
		return new MeetCustomer(tasksDepartment);
	}
}
