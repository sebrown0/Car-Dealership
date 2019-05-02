/**
 * 
 */
package people.employees;

import departments.department.EmployeeDetails;

/**
 * @author Brown
 *
 */
public interface IdleEmployee {//extends EmployeeDetails, TaskReceiver {
	EmployeeDetails nextEmployee();
}
