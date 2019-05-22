package departments.department;

public class ObjectDetails implements PersonDetails, EmployeeDetails, DepartmentDetails {

	// Person details
	private long id = -1;
	private String firstName;
	private String lastName;
	
	//Employee details
	private String role = "none";
	private String seniotity = "none";
	
	// Department details
	private String deptID = "";
	private String deptName = "";

	// Person details
	@Override
	public void setID(long id) {
		this.id = id;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public long getID() {
		return id;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String getFullName() {
		return firstName + " " + lastName;
	}

	//Employee details
	@Override
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String getRole() {
		return role;
	}		
	
	@Override
	public void setSeniority(String seniority) {
		this.seniotity = seniority;
	}

	@Override
	public String getSeniority() {
		return seniotity;
	}
	
	// Department details
	@Override
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Override
	public String getDeptID() {
		return deptID;
	}
		
	@Override
	public String getDeptName() {
		return deptName;
	}	
}
