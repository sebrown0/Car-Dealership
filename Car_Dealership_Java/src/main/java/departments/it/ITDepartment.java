/**
 * 
 */
package departments.it;

import departments.department.Department;
import object_details.EmployeeDetails;
import people.employees.Clerk;
import tasks.abstract_tasks.Task;

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
