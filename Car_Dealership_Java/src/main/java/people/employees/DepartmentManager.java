package people.employees;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import departments.department.Department;
import departments.department.EmployeeDetails;
import task_list.AssignedTaskHolder;
import task_list.ManagementTaskList;
import task_list.PendingTaskHolder;
import task_list.PendingTaskList;
import task_list.TaskPending;
import tasks.task_details.TaskReport;
import tasks.task_details.TaskReport.ReportBuilder;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 */
public class DepartmentManager extends Employee implements ManagersDuties{
	
	private PendingTaskList pendingTasks = new PendingTaskHolder();
	private ManagementTaskList assignedTasks = new AssignedTaskHolder();
	private ExecutorService taskExecutorService = Executors.newFixedThreadPool(20);
	
	public DepartmentManager(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails, department);
	}

	@Override
	public void delegateTask(Task t) {
		employeeLogEntry(this, "Delegating Task: " + t.objectID() );
		if(taskBelongsToThisDept(t)) {
			if(!taskAlreadyAssigned(t)) {
				delegateTaskToEmployee(t);
			} else {
				stashTask(TaskPending.DUPLICATE, t);
			}
		} else { 
			stashTask(TaskPending.WRONG_DEPT, t);
		}
	}

	private boolean taskBelongsToThisDept(Task t) {
		return (getDeptID().matches(t.getTasksDepartment().getDeptID())) ? true : false;
	}

	private boolean taskAlreadyAssigned(Task t) {
		employeeLogEntry(this, "Task: " + t.objectID() + " has already been assigned = " + assignedTasks.alreadyAssignedTask(t));
		return assignedTasks.alreadyAssignedTask(t);
	}
	
	private void delegateTaskToEmployee(Task t) {
		if(department.hasAnAvailableEmployee()) 
			assignTask(department.nextAvailableEmployee(), t);
		else 
			stashTask(TaskPending.NO_EMPLOYEE, t);
	}

	private void assignTask(Employee e, Task t) {
		employeeLogEntry(this, "Assigning Task" + t.objectID() + " to " + e.getFullName());
		department.addEmployeeToWorkingList(e);
		assignedTasks.addTask(e, t);
//		taskExecutorService.execute((e.accept(t)));
		new Thread(() -> e.accept(t)).start(); 
	}
	
	private void stashTask(TaskPending p, Task t) {
		employeeLogEntry(this, "Unable to assign Task" + t.objectID() + " because " + p.reason());
		pendingTasks.addTask(p, t);
		pendingTasks.logPendingTasks();
	}

	public void removeAssignedTask(Employee e, Task t) {
		TaskReport report = new ReportBuilder()
				.with(r -> {
					r.setCompletedAt(0);
					r.setEmployee(e);
					r.setTask(t);
					r.setTaskComplete(true);
				}).create();
		
		System.out.println(report);
		assignedTasks.removeAssignedTask(e);
		department.addEmployeeToIdleList(e);
	}
	
	@Override
	public void performTask(Task t) {
		employeeLogEntry(this, getFullName() + " is performing task: " + t.objectID());
		t.executeTask();
	}
}
