package departments.department;

public class ObjectDetails implements DepartmentDetails, PersonDetails, EmployeeDetails {
	// Department details
	private String deptId = "";
	private String deptName = "";
	
	//Employee details
	private String role = "none";
	private long id = -1;
	
	// Person details
	private String firstName;
	private String lastName;
	
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String getDeptId() {
		return deptId;
	}
	@Override
	public String getDeptName() {
		return deptName;
	}
	@Override
	public String getRole() {
		return role;
	}
	@Override
	public long getId() {
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
}
