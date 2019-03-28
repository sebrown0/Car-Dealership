/**
 * 
 */
package stock_department;

import department.Department;
import department_tasks.Task;
import employees.SalesPerson;

/**
 * @author Steve Brown
 *
 */
public class StockDept extends Department {

//	private static final String objId = "<Stock-Dept>"; TODO - Remove 
	public StockDept(String deptId, String deptName) {
		super(deptId, deptName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role)); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task task) {
		// TODO Auto-generated method stub
		
	}

//	private UpdateStock updateStock;
	
	
//	public void updateStock() {
//		updateStock = new UpdateStock(objId, spark(), dataBase());
//		updateStock.beginUpdate();
//	}
}
