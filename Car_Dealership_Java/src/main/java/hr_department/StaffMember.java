package hr_department;

// TODO - Comments
public interface StaffMember extends StaffDuty {
	long id();
	String firstName();
	String lastName();
	String role();
	String deptId();
}
