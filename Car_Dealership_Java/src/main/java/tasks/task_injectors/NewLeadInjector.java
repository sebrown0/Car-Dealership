package tasks.task_injectors;

import departments.department.Department;
import tasks.task_objects.NewLead;
import tasks.task_super_objects.AtomicTask;

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
