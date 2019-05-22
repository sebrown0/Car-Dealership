/**
 * 
 */
package departments.order;

import departments.department.Department;
import departments.department.EmployeeDetails;
import people.employees.Clerk;
import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDepartment extends Department  {
	
	public void newOrder(Order carOrderDetails) {
//		TaskProcessNewOrder newOrder = new TaskProcessNewOrder(carOrderDetails, database(), spark());
//		newOrder.begin();
	}

	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {				
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task task) {
		
	}

}
