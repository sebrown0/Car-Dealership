package departments.department;

public interface EmployeeDetails extends PersonDetails{
	
	void setRole(String role);
	
	void setSeniority(String seniority);
	
	void setDeptID(String deptID);
	
	String getRole();
	
	String getDeptID();
	
	String getSeniority();
}