package tasks.task_super_objects;

import departments.department.Department;
import tasks.task_details.TaskSchedule;
import tasks.task_details.TasksDetails;

public abstract class ScheduledTask extends Task implements Comparable<ScheduledTask>{

	protected TaskSchedule tasksSchedule;
	
	public ScheduledTask(TasksDetails tasksDetails, Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDetails, tasksDepartment);
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