package tasks.task_injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.task_super_objects.ManagementTask;

/**
 * @author Steve Brown
 *
 *  Injects a new Management task.
 */
public interface ManagementTaskInjector {
	ManagementTask getNewTask(DealerDAO dealerDAO);
	ManagementTask getNewTask(Department tasksDepartment);	
}
