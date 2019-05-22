/**
 * 
 */
package people.employees;

import departments.department.Department;
import departments.department.EmployeeDetails;
import people.Person;
import tasks.abstract_tasks.Task;
import tasks.details.ManagerReport;
import tasks.details.TaskReport.ReportBuilder;
import tasks.scheduler.EmployeeTaskReceiver;
import utils.logger.Loggable;

/**
 * @author Steve Brown
 *
 */
public abstract class Employee extends Person implements EmployeeDetails, Loggable, EmployeeTaskReceiver {

	protected Department department;
	protected EmployeeDetails empDetails;

	public Employee(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails);
		this.empDetails = employeeDetails;
		this.department = department;
	}

	public void employeeLogEntry(Loggable logData, String logEntry) {
		department.log().logEntry(logData, logEntry);
	}

	@Override
	public String getRole() {
		return empDetails.getRole();
	}

	@Override
	public String getDeptID() {
		return empDetails.getDeptID();
	}

	@Override
	public String getSeniority() {
		return empDetails.getSeniority();
	}

	@Override
	public void setRole(String role) {
		empDetails.setRole(role);
	}

	@Override
	public void setDeptID(String deptID) {
		empDetails.setDeptID(deptID);
	}

	@Override
	public void setSeniority(String seniority) {
		empDetails.setSeniority(seniority);
	}

	@Override
	public <T extends Task> void accept(T t, Manager manager) {
		employeeLogEntry(this, this.empDetails.getFullName() + " is executing task: " + t.objectID());
		t.executeTask();
		manager.giveReport(createReport(t));
	}
	
	private ManagerReport createReport(Task t) {
		return new ReportBuilder()
				.with(r -> {
					r.setCompletedAt(department.currentTime());
					r.setEmployee(this);
					r.setTask(t);
					r.setTaskComplete(true);
				}).create();
	}
}
