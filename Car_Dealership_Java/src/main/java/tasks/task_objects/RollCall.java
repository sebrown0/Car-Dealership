/**
 * 
 */
package tasks.task_objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import departments.department.Department;
import departments.department.EmployeeDetails;
import departments.department.ObjectDetails;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.HRDeptSP;
import task_strategy.TaskListVisitor;
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

	public RollCall( Department tasksDepartment) {
		super(tasksDepartment);
	}
	
	/*
	 * Update this department's available team members.
	 */
	private void updateTeam(ResultSet empRs) {
		try {
			while(empRs.next()) 
				addStaffMember(empRs);
		} catch (SQLException e) {
			ErrorHandler.checkError(ErrorCodes.TASK_ROLL_CALL, e.getMessage(), tasksDepartment.log());
		}
	}

	private void addStaffMember(ResultSet empRs) {
		EmployeeDetails emp = new ObjectDetails();
		try {
			emp.setDeptID(empRs.getString("dept_id"));
			emp.setLastName(empRs.getString("last_name"));
			emp.setID(empRs.getLong("hr_emp_id"));
			emp.setRole("role");
			emp.setFirstName(empRs.getString("first_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tasksDepartment.addDeptStaffMember(emp);
	}
	
	/*
	 *  Start the roll call for a department.
	 *  Build a SQL callable statement string using the department id as a parameter.
	 *  Execute the callable  statement.
	 */
	public void performRollCall() {
		tasksDepartment.log().logEntry(this, "Starting roll call for " + tasksDepartment.departmentDetails().getDeptName() + " department"); 
		tasksDepartment.database().dbConnect(); // TODO - Drop DB connection when finished. 
				
		String stmnt = QueryBuilder.build(tasksDepartment.departmentDetails().getDeptID(), HRDeptSP.ROLL_CALL.value());
		StoredProcedure emp = tasksDepartment.database().executeSP(stmnt);
		
		if(emp.errorCode() == ErrorCodes.NONE) 
			updateTeam(emp.getRs());
	}

	@Override
	public void executeTask() {
		performRollCall();		
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.addTask(this);
	}
}
