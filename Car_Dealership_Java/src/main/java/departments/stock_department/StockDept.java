/**
 * 
 */
package departments.stock_department;

import dealer_management.DealerDAO;
import departments.department.Department;
import departments.department.DepartmentDetails;
import departments.department.EmployeeDetails;
import people.employees.Clerk;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 */
public class StockDept extends Department {
 
	public StockDept(DepartmentDetails deptDetails, DealerDAO dealerDAO) {
		super(deptDetails, dealerDAO);
	}

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

//	private UpdateStock updateStock;
//	public void updateStock() {
//		updateStock = new UpdateStock(objId, spark(), dataBase());
//		updateStock.beginUpdate();
//	}
}
