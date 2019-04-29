package tasks.task_super_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import utils.Loggable;

public abstract class Task implements TaskRunner, Loggable {

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
}
