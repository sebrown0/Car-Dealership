/**
 * 
 */
package department_tasks;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import department.Department;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.HRDeptSP;

/**
 * @author Steve Brown
 *
 *	See which employees are fit for work, i.e. not sick* or on holiday.
 *		*(TODO - Not implemented yet)
 */
public class TaskRollCall implements Task{

	private final String objId;
	private Department department = null;
	
	public TaskRollCall(Department dept) {
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
		department.addTask(this);	// Add the task that is THIS task to the department's task list.
	}
	
	/*
	 * Update this department's available team members.
	 */
	private void updateTeam(ResultSet empRs) {
		try {
			while(empRs.next()) {
				
				department.addDeptStaffMember( 
						empRs.getLong("hr_emp_id"), 
						empRs.getString("first_name"), 
						empRs.getString("last_name"), 
						empRs.getString("dept_id"), 
						"role"
						);
				
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.TASK_ROLL_CALL, e.getMessage());
		}
	}

	public void rollCall() {
		department.log().logEntry(objId, "Starting roll call for " + department.deptName() + " department"); // TODO - ObjID
				
		department.database().dbConnect(); // TODO - Drop DB connection when finished. 
				
		String query = StoredProcedure.QueryBuilder.build(department.getDeptId(), HRDeptSP.ROLL_CALL.value());
		StoredProcedure emp = department.database().executeSP(query);
		
		if(emp.errorCode() == ErrorCodes.NONE) {
			updateTeam(emp.getRs());
		}			
	}

	@Override
	public void run() {
		rollCall();
	}

}
