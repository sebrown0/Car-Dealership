package tasks.concrete;

import java.util.List;

import dealer_management.DealerDAO;
import departments.department.Department;
import people.employees.Employee;
import tasks.abstract_tasks.ManagementTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 *	A management task that can be performed by a manager 
 *	or an employee of a department.
 */
public class LogDepartmentEmployees extends ManagementTask {
	
	// Use for a management task.
	public LogDepartmentEmployees(DealerDAO dealerDAO) {
		super(null);
		this.log = dealerDAO.getLog();
	}

	// Use for an atomic task.
	public LogDepartmentEmployees(Department tasksDepartment) {
		super(tasksDepartment);
		this.log = tasksDepartment.log();
	}

	@Override
	public void executeTask() {
		logDeptEmps();
	}
	
	private void logDeptEmps() {
		Department dept = tasksDepartment;
		if(dept != null) {
			log.logEntry(this, "Logging staff for (" + dept.getDeptName() + ") department");
			log.logEntry(this, "(" + dept.getDeptName() + ") manager is: " + dept.getDeptManager().getFullName());
			List<Employee> staff = dept.idleStaff().getTeam();
			for (Employee e : staff)
				log.logEntry(this, "(" + dept.getDeptName() + ") staff member: " + e.getFullName());
		}
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}

}
