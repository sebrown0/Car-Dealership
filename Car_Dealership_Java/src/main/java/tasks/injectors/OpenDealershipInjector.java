package tasks.injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.abstract_tasks.ManagementTask;
import tasks.concrete.OpenDealership;

/**
 * @author Steve Brown
 *
 *  Return a new task OpenDealership. 
 *  To be executed immediately.
 */
public class OpenDealershipInjector implements ManagementTaskInjector {

	@Override
	public ManagementTask getNewTask(Department tasksDepartment) {
		return new OpenDealership(tasksDepartment);
	}

	@Override
	public ManagementTask getNewTask(DealerDAO dealerDAO) {
		return new OpenDealership(dealerDAO);
	}
}
