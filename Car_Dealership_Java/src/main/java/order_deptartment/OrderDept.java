/**
 * 
 */
package order_deptartment;

import department.Department;
import hr_department.Employee;
import hr_department.NewEmployee;
import hr_department.StaffMember;
import sales_department.SalesPerson;

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
		
		ProcessNewOrder newOrder = new ProcessNewOrder(carOrderDetails, dataBase(), spark());
		newOrder.begin();
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {				
		staff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role)); // TODO - Change to proper employee
	}

}
