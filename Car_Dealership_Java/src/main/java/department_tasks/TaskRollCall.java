/**
 * 
 */
package department_tasks;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
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
public class TaskRollCall extends Task {

	private final String objId;
	private Department department = null;
	
	// TODO - NEW
//	public TaskRollCall() {
//		this.objId = "<" + "DEPTNAME" + ">" + " <" + this.getClass().getSimpleName() + ">";
//	}
	
	// TODO - Remove ?
	public TaskRollCall(Department dept) {
		super();
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
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
						"role" 							// TODO - Get the emp's role.
						);
				
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.TASK_ROLL_CALL, e.getMessage());
		}
	}

	/*
	 *  Start the roll call for a department.
	 *  Build a SQL callable statement string using the department id as a parameter.
	 *  Execute the callable  statement.
	 */
	public void performRollCall() {
		department.log().logEntry(objId, "Starting roll call for " + department.deptName() + " department"); // TODO - ObjID
		
		department.database().dbConnect(); // TODO - Drop DB connection when finished. 
				
		String stmnt = QueryBuilder.build(department.getDeptId(), HRDeptSP.ROLL_CALL.value());
		StoredProcedure emp = department.database().executeSP(stmnt);
		
		if(emp.errorCode() == ErrorCodes.NONE) {
			updateTeam(emp.getRs());
		}
	}

	@Override
	public void run() {
		performRollCall();		
	}

}
