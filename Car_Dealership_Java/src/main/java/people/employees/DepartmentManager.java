package people.employees;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import departments.department.Department;
import departments.department.EmployeeDetails;
import task_list.AssignedTaskHolder;
import task_list.ManagementTaskList;
import task_list.PendingTaskHolder;
import task_list.PendingTaskList;
import task_list.TaskPendingType;
import tasks.task_details.ManagerReport;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 * Assigns tasks to employees if any are available.
 * Tracks the task using the report sent back from the employee.
 */
public class DepartmentManager extends Employee implements ManagersDuties, Manager{
	
	private PendingTaskList pendingTasks = new PendingTaskHolder();
	private ManagementTaskList assignedTasks = new AssignedTaskHolder();
	private ExecutorService taskExecutorService = Executors.newFixedThreadPool(20);
	
	public DepartmentManager(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails, department);
	}

	@Override
	public void delegateTask(Task t) {
		employeeLogEntry(this, "Delegating Task: " + t.objectID());
		if(taskBelongsToThisDept(t)) {
			if(!taskAlreadyAssigned(t)) {
				delegateTaskToEmployee(t);
			} else {
				moveTaskToPending(TaskPendingType.DUPLICATE, t);
			}
		} else { 
			moveTaskToPending(TaskPendingType.WRONG_DEPT, t);
		}
	}

	private boolean taskBelongsToThisDept(Task t) {
		return (getDeptID().matches(t.getTasksDeptId())) ? true : false;
	}

	private boolean taskAlreadyAssigned(Task t) {
		return assignedTasks.alreadyAssignedTask(t);
	}
	
	private void delegateTaskToEmployee(Task t) {
		if(department.hasAnAvailableEmployee()) 
			assignTask(department.nextAvailableEmployee(), t);
		else 
			moveTaskToPending(TaskPendingType.NO_EMPLOYEE, t);
	}

	private void assignTask(Employee e, Task t) {
		employeeLogEntry(this, "Assigning Task" + t.objectID() + " to " + e.getFullName());
		department.addEmployeeToWorkingList(e);
		assignedTasks.addTask(e, t);
//		taskExecutorService.execute((e.accept(t)));
		new Thread(() -> e.accept(t, this)).start(); 
	}
	
	private void moveTaskToPending(TaskPendingType p, Task t) {
		employeeLogEntry(this, String.format("Task [%s] moved to pending because [%s]", 
				t.objectID(), p.reason()));
		pendingTasks.addTask(p, t);
		pendingTasks.logPendingTasks(department.log(), TaskPendingType.FAILED);
	}
	
	@Override
	public void giveReport(ManagerReport report) {
		if(report.isTaskComplete())
			markTaskAsComplete(report);
		else
			markTaskAsNotComplete(report);
		reassignTaskAndEmployee(report);
	}
	
	private void markTaskAsComplete(ManagerReport report) {
		logTaskReport(report, "completed task");
	}
	
	private void markTaskAsNotComplete(ManagerReport report) {
		moveTaskToPending(TaskPendingType.FAILED, report.getTask());
		logTaskReport(report, "did not complete task");
	}
	
	private void logTaskReport(ManagerReport report, String completionMsg) {
		String msg = String.format("Employee [%s] %s [%s]", 
				report.getEmployee().getFullName(), completionMsg, report.getTask().objectID());
		employeeLogEntry(this, msg);
	}
	
	private void reassignTaskAndEmployee(ManagerReport report) {
		department.addEmployeeToIdleList(report.getEmployee());
		assignedTasks.removeAssignedTask(report.getEmployee());
	}
	
	@Override
	public void performTask(Task t) {
		employeeLogEntry(this, String.format("Department manager [%s] is performing task [%s] ", 
				this.getFullName(), t.objectID()));
		t.executeTask();
	}
}
