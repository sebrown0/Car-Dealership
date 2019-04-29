/**
 * 
 */
package departments.hr_department;

import employees.EmployeeDetails;

/**
 * @author Steve Brown
 * 
 * POJO for anyone involved with the Dealership, i.e salesman or customer.
 */
public class Person{
	
	private long id;
	private String deptId;		// TODO - Change
	private String firstName;
	private String lastName;
	private String role;
		
	public Person(EmployeeDetails employeeDetails) {
		super();
		this.id = employeeDetails.getId();
		this.firstName = employeeDetails.getFirstName();
		this.lastName = employeeDetails.getLastName();
		this.deptId = employeeDetails.getDeptId();
		this.role = employeeDetails.getRole();
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// TODO - Roles and Departments should not be here!!!!!!!!!!
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}