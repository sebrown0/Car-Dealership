package tasks.injectors;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.abstract_tasks.ManagementTask;
import tasks.concrete.LogDepartmentEmployees;

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
