/**
 * 
 */
package order_deptartment;

import department.Department;
import department_tasks.Task;
import employees.Employee;
import employees.SalesPerson;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDept extends Department  {
	
//	private static final String objId = "<Order-Dept>"; TODO - Remove
	
	public OrderDept(String deptId, String deptName) {
		super(deptId, deptName);
		// TODO Auto-generated constructor stub
	}

	public void newOrder(Order carOrderDetails) {
		
		// TODO - Add this as a task.
		
		TaskProcessNewOrder newOrder = new TaskProcessNewOrder(carOrderDetails, dataBase(), spark());
		newOrder.begin();
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {				
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role)); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task task) {
		// TODO Auto-generated method stub
		
	}

}
