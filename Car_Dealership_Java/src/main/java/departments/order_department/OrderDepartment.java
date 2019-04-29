/**
 * 
 */
package departments.order_department;

import dealer_management.DealerDAO;
import departments.department.Department;
import departments.department.DepartmentDetails;
import departments.department.EmployeeDetails;
import people.employees.Clerk;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDepartment extends Department  {
		
	public OrderDepartment(DepartmentDetails deptDetails, DealerDAO dealerDAO) {
		super(deptDetails, dealerDAO);	
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Task> void accept(T t) {
		// TODO Auto-generated method stub
		
	}

}
