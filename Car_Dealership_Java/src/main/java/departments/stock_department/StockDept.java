/**
 * 
 */
package departments.stock_department;

import dealer_management.DealerDAO;
import department_tasks.Task_NOB;
import departments.department.Department;
import employees.SalesPerson;

/**
 * @author Steve Brown
 *
 */
public class StockDept extends Department {

//	private static final String objId = "<Stock-Dept>"; TODO - Remove 
	public StockDept(String deptId, String deptName, DealerDAO dealerDAO) {
		super(deptId, deptName, dealerDAO);
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role), log); // TODO - Change to proper employee
	}

	@Override
	public void delegateTask(Task_NOB task) {
		// TODO Auto-generated method stub
		
	}

//	private UpdateStock updateStock;
	
	
//	public void updateStock() {
//		updateStock = new UpdateStock(objId, spark(), dataBase());
//		updateStock.beginUpdate();
//	}
}
