package tasks.task_injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.task_objects.LogDepartmentEmployees;
import tasks.task_super_objects.ManagementTask;

/**
 * @author Steve Brown
 *
 *
 */
public class LogDepartmentEmployeesInjector implements ManagementTaskInjector {

	@Override
	public ManagementTask getNewTask(Department tasksDepartment) {
		return new LogDepartmentEmployees(tasksDepartment);
	}

	@Override
	public ManagementTask getNewTask(DealerDAO dealerDAO) {
		return new LogDepartmentEmployees(dealerDAO);
	}
}
