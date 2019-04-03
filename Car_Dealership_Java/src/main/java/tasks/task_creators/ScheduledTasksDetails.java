package tasks.task_creators;

public interface ScheduledTasksDetails extends DepartmentTasksDetails {

	TaskSchedule getTaskSchedule();

	void setTaskSchedule(TaskSchedule taskSchedule);

}