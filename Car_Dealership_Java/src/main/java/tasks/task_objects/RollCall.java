/**
 * 
 */
package tasks.task_objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import departments.department.Department;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.HRDeptSP;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 * 
 *	An Atomic task that belongs to a department.
 *
 *	See which employees are fit for work, i.e. not sick* or on holiday.
 *		*(TODO - Not implemented yet)
 */
public class RollCall extends AtomicTask { 

	public RollCall(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
	}

	/*
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return RollCall.class.getSimpleName();
	}
	
	/*
	 * Update this department's available team members.
	 */
	private void updateTeam(ResultSet empRs) {
		try {
			while(empRs.next()) {
				
//				tasksDepartment.addDeptStaffMember(
				tasksDetails.getDepartment().addDeptStaffMember(
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
		tasksDepartment.log().logEntry(tasksDetails.getTaskID(), "Starting roll call for " + tasksDepartment.deptName() + " department"); 
		
		tasksDepartment.database().dbConnect(); // TODO - Drop DB connection when finished. 
				
		String stmnt = QueryBuilder.build(tasksDepartment.getDeptId(), HRDeptSP.ROLL_CALL.value());
		StoredProcedure emp = tasksDepartment.database().executeSP(stmnt);
		
		if(emp.errorCode() == ErrorCodes.NONE) {
			updateTeam(emp.getRs());
		}
	}

	@Override
	public void executeTask() {
		performRollCall();		
	}
}
