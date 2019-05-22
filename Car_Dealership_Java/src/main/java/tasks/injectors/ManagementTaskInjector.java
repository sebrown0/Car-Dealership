package tasks.injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.abstract_tasks.ManagementTask;

/**
 * @author Steve Brown
 *
 *  Injects a new Management task.
 */
public interface ManagementTaskInjector {
	ManagementTask getNewTask(DealerDAO dealerDAO);
	ManagementTask getNewTask(Department tasksDepartment);	
}
