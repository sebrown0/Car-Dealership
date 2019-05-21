/**
 * 
 */
package departments.it_department;

import departments.department.Department;
import departments.department.EmployeeDetails;
import people.employees.Clerk;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *  
 */
public class ITDepartment extends Department  {
		
	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {				
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task task) {
		
	}

}
