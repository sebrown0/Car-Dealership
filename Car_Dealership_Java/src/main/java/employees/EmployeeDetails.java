package employees;

public class EmployeeDetails extends PersonDetails{

	private long id = -1; 
	private String deptId = "none"; 
	private String role = "none";
	
//	public EmployeeDetails(PersonDetails personDetails, long id, String deptId, String role) {
//		super(personDetails.getFirstName(), personDetails.getLastName());
//		this.id = id;
//		this.deptId = deptId;
//		this.role = role;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	
}
