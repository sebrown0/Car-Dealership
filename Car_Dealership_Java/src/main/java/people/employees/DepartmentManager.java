package people.employees;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import departments.department.Department;
import object_details.EmployeeDetails;
import tasks.abstract_tasks.Task;
import tasks.details.ManagersTaskReport;
import tasks.lists.AssignedTaskHolder;
import tasks.lists.ManagementTaskList;
import tasks.lists.PendingTaskHolder;
import tasks.lists.PendingTaskList;
import tasks.lists.TaskPendingType;

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
			if(!taskAlreadyInAssignedList(t)) {
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
	
	private boolean taskAlreadyInAssignedList(Task t) {
		return assignedTasks.alreadyAssignedTask(t);
	}
	
	private void delegateTaskToEmployee(Task t) {
		if(department.hasAnAvailableEmployee()) 
			assignTask(department.nextAvailableEmployee(), t);
		else 
			moveTaskToPending(TaskPendingType.NO_EMPLOYEE, t);
	}

	private void assignTask(Employee e, Task t) {
		logTaskAssigned(t, e, "Assigning Task");
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
	public void giveReport(ManagersTaskReport report) {
		reassignTaskAndEmployee(report);	
		if(report.isTaskComplete()) 
			markTaskAsSuccessful(report);
		else 
			markTaskFailed(report);
	}
	
	/*
	 *  Always reassign the task and employee even if the task failed.
	 */
	private void reassignTaskAndEmployee(ManagersTaskReport report) {
		department.addEmployeeToIdleList(report.getEmployee());
		assignedTasks.removeAssignedTask(report.getEmployee());
	}
	
	private void markTaskAsSuccessful(ManagersTaskReport report) {
		logTaskReport(report, "completed task.");
		if(report.getTask().isTaskExtendible()) 
			delegateFollowOnTask(report);
	}
	
	private void markTaskFailed(ManagersTaskReport report) {
		moveTaskToPending(TaskPendingType.FAILED, report.getTask());
		logTaskReport(report, "did not complete task");
	}
		
	private void delegateFollowOnTask(ManagersTaskReport report) {
		logTaskAssigned(report.getTask().getThisTasksFollowOnTask(), report.getEmployee(), "Delegating follow up task");
		if(report.getEmployee().isEmployeeBusy())
			moveTaskToPending(TaskPendingType.FOLLOW_ON_EMP_BUSY, report.getTask());
		else
			assignTask(report.getEmployee(), report.getTask().getThisTasksFollowOnTask());
	}
	
	private void logTaskReport(ManagersTaskReport report, String completionMsg) {
		String msg = String.format("Employee [%s] %s [%s]", 
				report.getEmployee().getFullName(), completionMsg, report.getTask().objectID());
		employeeLogEntry(this, msg);
	}
	
	private void logTaskAssigned(Task t, Employee e, String msg) {
		employeeLogEntry(this, String.format("%s [%s] to [%s]", msg, t.objectID(), e.getFullName()));
	}
		
	@Override
	public void performTask(Task t) {
		employeeLogEntry(this, String.format("Department manager [%s] is performing task [%s] ", 
				this.getFullName(), t.objectID()));
		t.executeTask();
	}
}
