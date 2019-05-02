package departments.hr_department;

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

public class HRDeptartment extends Department {

	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); 
	}

	@Override
	public void delegateTask(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public <T extends Task> void accept(T t) {
		// TODO Auto-generated method stub
	}
}
