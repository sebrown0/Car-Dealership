/**
 * 
 */
package people.employees;

import departments.department.Department;
import departments.department.EmployeeDetails;
import people.Person;
import utils.Loggable;

/**
 * @author Steve Brown
 *
 */
public abstract class Employee extends Person implements EmployeeDetails, Loggable  { 

	protected Department department;
	protected EmployeeDetails empDetails;

	public Employee(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails);
		this.empDetails = employeeDetails;
		this.department = department;
	}

	@Override
	public String getRole() {
		return empDetails.getRole();
	}

	@Override
	public String getDeptID() {
		return empDetails.getDeptID();
	}

	@Override
	public void setRole(String role) {
		empDetails.setRole(role);
	}

	@Override
	public void setDeptID(String deptID) {
		empDetails.setDeptID(deptID);
	}

}
