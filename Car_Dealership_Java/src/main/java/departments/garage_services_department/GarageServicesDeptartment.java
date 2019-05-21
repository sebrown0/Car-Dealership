package departments.garage_services_department;

import departments.department.Department;
import departments.department.EmployeeDetails;
import people.employees.Clerk;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 * Responsible for managing people within the Car Dealership.
 * 
 */

public class GarageServicesDeptartment extends Department {

	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); 
	}

	@Override
	public void delegateTask(Task task) {
		
	}
}
