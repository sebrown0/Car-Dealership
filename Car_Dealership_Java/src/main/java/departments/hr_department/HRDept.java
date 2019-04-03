package departments.hr_department;

import dealer_management.DealerDAO;
import department_tasks.Task_NOB;
import departments.department.Department;
import employees.SalesPerson;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept extends Department {

	public HRDept(String deptId, String deptName, DealerDAO dealerDAO) {
		super(deptId, deptName, dealerDAO);
	}

	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {			
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role), log); // TODO - Change to HR employee
	}

	@Override
	public void delegateTask(Task_NOB task) {
		// TODO Auto-generated method stub
		
	}

}
