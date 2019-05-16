package tasks.task_super_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import utils.logger.Loggable;

public abstract class Task implements TaskRunner, Loggable, Comparable<ScheduledTask> {

	protected Department tasksDepartment;
	
	public Task(Department tasksDepartment) {
		this.tasksDepartment = tasksDepartment;
	}
	
	public Department getTasksDepartment() {
		return tasksDepartment;
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
