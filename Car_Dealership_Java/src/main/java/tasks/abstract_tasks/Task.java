package tasks.abstract_tasks;

import departments.department.Department;
import tasks.strategy.TaskListVisitor;
import utils.logger.Loggable;

public abstract class Task implements TaskRunner, Loggable, Comparable<ScheduledTask> {

	protected Department tasksDepartment;
	
	public Task(Department tasksDepartment) {
		this.tasksDepartment = tasksDepartment;
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

	@Override
	public int compareTo(ScheduledTask o) {
		return 0;
	}
}
