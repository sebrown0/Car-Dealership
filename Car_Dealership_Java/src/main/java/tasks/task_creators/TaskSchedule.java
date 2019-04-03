/**
 * 
 */
package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *         Used to retrieve information about a task's scheduled: Start Time.
 *         End Time. Duration.
 */
public interface TaskSchedule {

	/*
	 * Set Task's scheduled time of execution.
	 */
//	void setScheduledTime(TimeFormatter scheduledTime);

	/*
	 * Get Task's scheduled duration in seconds.
	 */
	long scheduledDuration();

	/*
	 * Get Task's scheduled end time in seconds.
	 */
	int scheduledEndTime();

	/*
	 * Get Task's scheduled start time of execution in seconds.
	 */
	int scheduledStartTime();

}
