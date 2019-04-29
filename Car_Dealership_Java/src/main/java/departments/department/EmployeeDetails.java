package departments.department;

public interface EmployeeDetails extends PersonDetails{
	
	void setRole(String role);
	
	void setDeptID(String deptID);
	
	String getRole();
	
	String getDeptID();
}