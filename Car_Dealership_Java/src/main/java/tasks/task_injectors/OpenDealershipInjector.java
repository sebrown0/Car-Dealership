package tasks.task_injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.task_objects.OpenDealership;
import tasks.task_super_objects.ManagementTask;

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
