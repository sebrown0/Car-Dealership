package hr_department;

import department.Department;
import sales_department.SalesPerson;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept extends Department {

	public HRDept(String deptId, String deptName) {
		super(deptId, deptName);
	}

	
	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {			
		staff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role)); // TODO - Change to proper employee
	}

}
