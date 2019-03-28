package hr_department;

import department.Department;

// TODO - Comments
// TODO - Remove
public interface StaffMember extends StaffDuty {
//public interface StaffMember  {
	long id();
	String firstName();
	String lastName();
	String role();
	String deptId();
	Department employeeDepartment();
}
