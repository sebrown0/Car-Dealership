package tasks.task_injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.task_objects.RollCall;
import tasks.task_super_objects.ManagementTask;

/**
 * @author Steve Brown
 *
 *  Return a new task RollCall for a Department. 
 */
public class RollCallInjector implements ManagementTaskInjector {

	@Override
	public ManagementTask getNewTask(Department tasksDepartment) {
		return new RollCall(tasksDepartment);
	}
	
	@Override
	public ManagementTask getNewTask(DealerDAO dealerDAO) {
		return new RollCall(dealerDAO);
	}
}
