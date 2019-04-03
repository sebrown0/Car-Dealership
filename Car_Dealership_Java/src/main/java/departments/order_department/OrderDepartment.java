/**
 * 
 */
package departments.order_department;

import dealer_management.DealerDAO;
import department_tasks.Task_NOB;
import departments.department.Department;
import employees.SalesPerson;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDepartment extends Department  {
	
//	private static final String objId = "<Order-Dept>"; TODO - Remove
	
	public OrderDepartment(String deptId, String deptName, DealerDAO dealerDAO) {
		super(deptId, deptName, dealerDAO);
		
	}

	public void newOrder(Order carOrderDetails) {
		
		// TODO - Add this as a task.
		
//		TaskProcessNewOrder newOrder = new TaskProcessNewOrder(carOrderDetails, database(), spark());
//		newOrder.begin();
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {				
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role), log); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task_NOB task) {
		// TODO Auto-generated method stub
		
	}

}
