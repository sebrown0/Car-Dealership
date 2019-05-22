package tasks.injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.abstract_tasks.ManagementTask;
import tasks.concrete.CloseDealership;

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
