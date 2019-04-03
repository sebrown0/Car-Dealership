/**
 * 
 */
package departments.hr_department;

import java.util.ArrayList;
import java.util.List;

import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 * 	A list of employees that are available for work.
 * 	Each department has an EmployeePool (Team).
 */
public class Staff {

	private List<StaffMember> team;		// TODO - Blocking queue?
//	private Log log = new Logger(false);
	
	public Staff() {
		team = new ArrayList<StaffMember>();
	}
	
//	public StaffMember getStaffMember() {
//		return team.get(0);
//	}

	public void addDepStaffMember(StaffMember tm, Log log) {
		log.logEntry("\t" + tm.deptId(), 
				"Emp ID: " + tm.id() + 
				" Emp: " +  tm.firstName() + " " + tm.lastName() +
				" Role: " + tm.role());
		team.add(tm);	
	}
	
	public StaffMember nextEmployee() {
		if(!team.isEmpty()) {
			return team.get(0);
		}
		
		return null;
	}

	public List<StaffMember> getTeam() {
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
