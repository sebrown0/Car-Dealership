package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *  Injects a new scheduled task for a department.
 *  Only departments can schedule tasks.
 *   	- See drawing TaskIoD.uxf.
 */
public interface ScheduledTaskInjector {

	/*
	 * A new task for the specified department with a scheduled time or duration.
	 */
	TaskConsumer getNewTask(ScheduledTasksDetails taskDetails);
}
