/**
 * 
 */
package departments.hr_department;

import java.util.ArrayList;
import java.util.List;

import departments.department.EmployeeDetails;
import utils.Log;
import utils.Loggable;

/**
 * @author Steve Brown
 *
 * 	A list of employees that are available for work.
 * 	Each department has an EmployeePool (Team).
 */
public class DepartmentStaff implements Loggable{

	private List<EmployeeDetails> team;		
	
	public DepartmentStaff() {
		team = new ArrayList<EmployeeDetails>();
	}

	public void addDepStaffMember(EmployeeDetails emp, Log log) {
		log.logEntry(this, 
				"Adding -> Emp ID: " + emp.getID() + 
				" Emp: " +  emp.getFirstName() + " " + emp.getLastName() +
				" Role: " + emp.getRole() + ". To the team");
		team.add(emp);	
	}
	
	public EmployeeDetails nextEmployee() {
		if(!team.isEmpty())
			return team.get(0);
		
		return null;
	}

	public List<EmployeeDetails> getTeam() {
		return team;
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

//	public boolean removeEmployee(long empId) {
//		
//		if(!team.isEmpty()) {
//			return team.removeIf(e -> e.getId() == empId);
//		}
//		return false;
//	}

//	@Override

}
