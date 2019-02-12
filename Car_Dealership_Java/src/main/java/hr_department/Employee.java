/**
 * 
 */
package hr_department;

/**
 * @author Steve Brown
 *
 */
public abstract class Employee extends Person implements EmployeesDuties{

	private boolean empWorking = false;
	
	public Employee(long id, String firstName, String lastName, String role) {
		super(id, firstName, lastName, role);
	}

	public Employee(String firstName, String lastName) {
		super(firstName, lastName);
	
	}

	public boolean isEmpWorking() {
		return empWorking;
	}

	public void setEmpWorking(boolean empWorking) {
		this.empWorking = empWorking;
	}
	
}
