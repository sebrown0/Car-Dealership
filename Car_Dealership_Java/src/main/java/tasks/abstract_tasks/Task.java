package tasks.abstract_tasks;

import departments.department.Department;
import people.employees.Employee;
import tasks.strategy.TaskListVisitor;
import utils.logger.Loggable;

public abstract class Task implements IsNotAnExtendibleTask, TaskRunner, Loggable, Comparable<ScheduledTask> {

	protected Department tasksDepartment;
	protected Employee assignedEmployee;
	private Task followOnTask;
	
	public Task(Department tasksDepartment) {
		this.tasksDepartment = tasksDepartment;
	}
	
	public boolean taskHasAnEmployeeAssigned() {
		return (assignedEmployee == null) ? false : true;
	}
	
	public Employee assignedEmployee() {
		return assignedEmployee;
	}
	
	public void setThisTasksFollowOnTask(Task t) {
		this.followOnTask = t;
	}
	
	public Task getThisTasksFollowOnTask() {
		return followOnTask;
	}
	
	public Department getTasksDepartment() {
		return tasksDepartment;
	}
	
	public String getTasksDeptId() {
		return tasksDepartment.getDeptID();
	}

	public void setTasksDepartment(Department tasksDepartment) {
		this.tasksDepartment = tasksDepartment;
	}
	
	public abstract <T extends TaskListVisitor> void accept(T taskList);
	
	public void visit(Employee employee) {
		this.assignedEmployee = employee;
	}

	@Override
	public int compareTo(ScheduledTask o) {
		return 0;
	}
}
