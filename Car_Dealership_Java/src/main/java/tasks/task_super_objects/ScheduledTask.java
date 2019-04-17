package tasks.task_super_objects;

import departments.department.Department;
import tasks.task_details.TaskSchedule;
import tasks.task_details.TasksDetails;

public abstract class ScheduledTask extends Task {

	protected TaskSchedule tasksSchedule;
	
	public ScheduledTask(TasksDetails tasksDetails, Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDetails, tasksDepartment);
		this.tasksSchedule = tasksSchedule;
	}

	public TaskSchedule getTasksSchedule() {
		return tasksSchedule;
	}

	public void setTasksSchedule(TaskSchedule tasksSchedule) {
		this.tasksSchedule = tasksSchedule;
	}
	
}