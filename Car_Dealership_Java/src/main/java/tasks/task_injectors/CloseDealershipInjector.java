package tasks.task_injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.task_objects.CloseDealership;
import tasks.task_super_objects.ManagementTask;

/**
 * @author Steve Brown
 *
 *  Return a new task CloseDealership.
 */
public class CloseDealershipInjector implements ManagementTaskInjector {

	@Override
	public ManagementTask getNewTask(Department tasksDepartment) {
		return new CloseDealership(tasksDepartment);
	}
	
	@Override
	public ManagementTask getNewTask(DealerDAO dealerDAO) {
		return new CloseDealership(dealerDAO);
	}

}
