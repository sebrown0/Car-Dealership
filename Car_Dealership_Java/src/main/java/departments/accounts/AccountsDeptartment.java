package departments.accounts;

import departments.department.Department;
import object_details.EmployeeDetails;
import people.employees.Clerk;
import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 *
 * Responsible for managing people within the Car Dealership.
 * 
 */

public class AccountsDeptartment extends Department {

	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); 
	}

	@Override
	public void delegateTask(Task task) {

	}

}
