/**
 * 
 */
package departments.hr_department;

import java.util.ArrayList;
import java.util.List;

import people.employees.Employee;
import utils.logger.Log;
import utils.logger.Loggable;

/**
 * @author Steve Brown
 *
 * 	A list of employees that are available for work.
 * 	Each department has an EmployeePool (Team).
 */
public class DepartmentStaff  implements Loggable {

	private List<Employee> team;		
	
	public DepartmentStaff() {
		team = new ArrayList<Employee>();
	}

	public void addDepStaffMember(Employee emp) {
		team.add(emp);	
	}
	
	public void addDepStaffMember(Employee emp, Log log) {
//		log.logEntry(this, 
//				"Adding -> Emp ID: " + emp.getID() + 
//				" Emp: " +  emp.getFirstName() + " " + emp.getLastName() +
//				" Role: " + emp.getRole() + 
//				" Seniority: " + emp.getSeniority());
		team.add(emp);	
	}
		
	public Employee nextEmployee() {
		if(!team.isEmpty()) 
			return team.get(0);
		
		return null;
	}

	public boolean staffListNotEmpty() {
		return !team.isEmpty();
	}
	
	public List<Employee> getTeam() {
		return team;
	}
	
	public boolean removeEmployee(long empId) {
		if(staffListNotEmpty())
			return team.removeIf(e -> e.getID() == empId);
		return false;
	}
	
//	public Employee getEmployee(long empId) {
//		if(!team.isEmpty()) {
//			for (TeamMember e : team) {
//				if(e.getId() == empId)
//					return e;
//			}
//		}
//		return null;
//	}

	
}
