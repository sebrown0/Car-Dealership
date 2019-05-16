package tasks.task_super_objects;

import departments.department.Department;
import tasks.task_details.TaskSchedule;

public abstract class ScheduledTask extends Task {

	protected TaskSchedule tasksSchedule;
	
	public ScheduledTask(Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDepartment);
		this.tasksSchedule = tasksSchedule;
	}
	
	public int getTaskStartTime() {
		return tasksSchedule.scheduledStartTime();
	}

	public TaskSchedule getTasksSchedule() {
		return tasksSchedule;
	}

	public void setTasksSchedule(TaskSchedule tasksSchedule) {
		this.tasksSchedule = tasksSchedule;
	}
	
	@Override
	public int compareTo(ScheduledTask t) {
		return (this.getTaskStartTime() > t.getTaskStartTime()) ? 1 : -1; 
	}
	
	public abstract int getStartTime();
}