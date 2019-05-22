/**
 * 
 */
package tasks.concrete;

import java.sql.ResultSet;

import dealer_management.DealerDAO;
import departments.department.Department;
import enums.HRDeptSP;
import tasks.abstract_tasks.ManagementTask;
import tasks.helpers.CreateEmployee;
import tasks.helpers.CreateEmployeeHelper;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 * 
 *	An Atomic task that belongs to a department.
 *
 *	See which employees are fit for work, i.e. not sick* or on holiday.
 *		*(TODO - Not implemented yet)
 */
public class RollCall extends ManagementTask { 

	// Use for a management task.
	public RollCall(DealerDAO dealerDAO) {
		super(null);
		this.log = dealerDAO.getLog();
	}
	
	// Use for an atomic task.
	public RollCall(Department tasksDepartment) {
		super(tasksDepartment);
	}
	
	@Override
	public void executeTask() {
		performRollCall();		
	}
	
	private void performRollCall() {
		tasksDepartment.log().logEntry(this, "Starting roll call for " + tasksDepartment.getDeptName() + " department"); 
		CreateEmployeeHelper helper = new CreateEmployee(tasksDepartment);
		ResultSet empRS = helper.getEmployeesFromDB(tasksDepartment.getDeptID(), HRDeptSP.ROLL_CALL); 
		if(empRS != null) 
			helper.updateTeam(empRS);
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
}
