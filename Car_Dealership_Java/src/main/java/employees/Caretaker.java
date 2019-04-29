/**
 * 
 */
package employees;

import departments.department.Department;

/**
 * @author Steve Brown
 *
 *	Handles non-department specific tasks for the business. 
 *
 *	Use when no other employees are available, i.e. at the beginning of the day.
 */
public class Caretaker extends Employee {

	public Caretaker(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails, department);
	}

}
