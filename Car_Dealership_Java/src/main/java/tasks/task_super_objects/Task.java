package tasks.task_super_objects;

import departments.department.Department;
import tasks.task_details.TasksDetails;

public abstract class Task implements TaskRunner {

	protected TasksDetails tasksDetails;
	protected Department tasksDepartment;
	
	public Task(TasksDetails tasksDetails, Department tasksDepartment) {
		this.tasksDetails = tasksDetails;
		this.tasksDepartment = tasksDepartment;
	}

	public Department getTasksDepartment() {
		return tasksDepartment;
	}

	public void setTasksDepartment(Department tasksDepartment) {
		this.tasksDepartment = tasksDepartment;
	}

	public void setTasksDetails(TasksDetails tasksDetails) {
		this.tasksDetails = tasksDetails;
	}
	
	public TasksDetails getTasksDetails() {
		return tasksDetails;
	}
}
