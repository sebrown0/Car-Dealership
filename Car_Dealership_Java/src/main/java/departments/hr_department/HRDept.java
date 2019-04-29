package departments.hr_department;

import dealer_management.DealerDAO;
import departments.department.Department;
import departments.department.DepartmentDetails;
import employees.Clerk;
import employees.EmployeeDetails;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept extends Department {

	public HRDept(DepartmentDetails deptDetails, DealerDAO dealerDAO) {
		super(deptDetails, dealerDAO);
	}

	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {			
		idleStaff().addDepStaffMember(new Clerk(employeeDetails, this), log); // TODO - Change to HR employee
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
