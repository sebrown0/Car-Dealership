/**
 * 
 */
package tasks.task_objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import tasks.task_creators.DepartmentTasksDetails;
import enums.HRDeptSP;

/**
 * @author Steve Brown
 * 
 *	An Atomic task that belongs to a department.
 *
 *	See which employees are fit for work, i.e. not sick* or on holiday.
 *		*(TODO - Not implemented yet)
 */
public class RollCall extends DepartmentTask {

	/**
	 * @param tasksDetails: Details for this department task.
	 */
	public RollCall(DepartmentTasksDetails tasksDetails) {
		super(tasksDetails);
	}

	/*
	 * Update this department's available team members.
	 */
	private void updateTeam(ResultSet empRs) {
		try {
			while(empRs.next()) {
				
				tasksDepartment.addDeptStaffMember( 
						empRs.getLong("hr_emp_id"), 
						empRs.getString("first_name"), 
						empRs.getString("last_name"), 
						empRs.getString("dept_id"), 
						"role" 							// TODO - Get the emp's role.
						);
				
			}
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.TASK_ROLL_CALL, e.getMessage(), tasksDepartment.log());
		}
	}

	/*
	 *  Start the roll call for a department.
	 *  Build a SQL callable statement string using the department id as a parameter.
	 *  Execute the callable  statement.
	 */
	public void performRollCall() {
		tasksDepartment.log().logEntry(tasksDetails.getObjId(), "Starting roll call for " + tasksDepartment.deptName() + " department"); 
		
		tasksDepartment.database().dbConnect(); // TODO - Drop DB connection when finished. 
				
		String stmnt = QueryBuilder.build(tasksDepartment.getDeptId(), HRDeptSP.ROLL_CALL.value());
		StoredProcedure emp = tasksDepartment.database().executeSP(stmnt);
		
		if(emp.errorCode() == ErrorCodes.NONE) {
			updateTeam(emp.getRs());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		performRollCall();		
	}

}
