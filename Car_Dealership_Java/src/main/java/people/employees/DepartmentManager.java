package people.employees;

import departments.department.Department;
import departments.department.EmployeeDetails;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 */
public class DepartmentManager extends Employee implements ManagersDuties{
	
	public DepartmentManager(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails, department);
	}

	@Override
	public void delegateTask(Task t) {
		if(taskBelongsToThisDept(t)) 
			delegateTaskToEmployee(t);
		else 
			stashTask(t);
	}

	private boolean taskBelongsToThisDept(Task t) {
		return (getDeptID().matches(t.getTasksDepartment().getDeptID())) ? true : false;
	}
	
	private void delegateTaskToEmployee(Task t) {
		Employee emp = null;
		if(department.hasAnAvailableEmployee()) {
			emp = department.nextAvailableEmployee();
			assignTask(emp, t);
		}else {
			stashTask(t);
		}
	}

	private void stashTask(Task t) {
		employeeLogEntry(this, "Unable to assign Task" + t.objectID() + "to an employee");
	}

	private void assignTask(Employee emp, Task t) {
		employeeLogEntry(this, "Assigning Task" + t.objectID() + " to " + emp.getFullName());
		emp.accept(t);
	}

	@Override
	public void performTask(Task t) {
		employeeLogEntry(this, getFullName() + " is performing task: " + t.objectID());
		t.executeTask();
	}
}
