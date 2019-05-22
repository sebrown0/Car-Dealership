package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;
import tasks.concrete.NewLead;

/**
 * @author Steve Brown
 *
 *  Return a new task NewLead. 
 *  To be executed immediately.
 */
public class NewLeadInjector implements AtomicTaskInjector {

	@Override
	public AtomicTask getNewTask(Department tasksDepartment) {
		return new NewLead(tasksDepartment);
	}
}
