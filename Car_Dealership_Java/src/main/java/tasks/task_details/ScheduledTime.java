/**
 * 
 */
package tasks.task_details;

import time.MutableTime;

/**
 * @author Steve Brown
 * 
 * 		Schedule for a task. Every scheduled task should have one. Atomic
 *      tasks i.e. those that are to be executed immediately do not need a schedule.
 * 
 *     	A task's duration is set at instantiation. If the start and end times
 *      are erroneous then task's duration is set to -1 and therefore won't
 *      be executed.
 * 
 *      Type of schedule: 
 *         	1. 	An atomic task with a duration: 
 *         			Construct with valid duration. 
 *         	2. 	Scheduled task with start/end time specified in	seconds: 
 *         			Construct with start time (>=0) and end time (<= 86400). 
 *         	3.  Scheduled task with start/end time specified as formatted strings (TimeFormatter): 
 *         			Construct with start time (h,m,s) and end time (h,m,s).
 */
public class ScheduledTime implements TaskSchedule {

	private int startTimeSeconds = 0; 				// Task's start time. Value 1 - 86400.
	private int endTimeSeconds = 0; 				// Task's end time. Value 1 - 86400.
	private long durationSeconds = -1; 				// Duration (end - start).

	private MutableTime startTimeFormatter = null; 	// Start time as a formatted string.
	private MutableTime endTimeFormatter = null; 	// End time as a formatted string.

	/*
	 * Construct a task's schedule from start and end time in seconds.
	 */
	public ScheduledTime(int startTimeSeconds, int endTimeSeconds) {
		this.startTimeSeconds = startTimeSeconds;
		this.endTimeSeconds = endTimeSeconds;

		calculateDuration(startTimeSeconds, endTimeSeconds);
	}

	/*
	 * An atomic task with a specified duration. If the specified duration is not
	 * within the number of seconds in a day then the duration is set to 0.
	 */
	public ScheduledTime(long durationSeconds) {
		// Calculate (check) the tasks duration using the same logic as if there was a
		// start and end.
		calculateDuration(0, durationSeconds);
	}

	/*
	 * Construct a task's schedule from formated start and end time (in seconds).
	 */
	public ScheduledTime(MutableTime startTimeFormatter, MutableTime endTimeFormatter) {
		this.startTimeFormatter = startTimeFormatter;
		this.endTimeFormatter = endTimeFormatter;

		if (startTimeFormatter != null && endTimeFormatter != null) {
			// There is a start and end time. See if they're valid.
			this.startTimeSeconds = startTimeFormatter.seconds();
			this.endTimeSeconds = endTimeFormatter.seconds();
			calculateDuration(startTimeSeconds, endTimeSeconds);
		}
	}

	/*
	 * Calculate the task's duration in seconds, from the given start and end times
	 * (in seconds). Return -1 if the task's duration is trying to be set to less
	 * than a day or more than a day.
	 */
	private void calculateDuration(long start, long end) {
		this.durationSeconds = (((end - start) >= 0) && ((end - start) <= 86400)) ? end - start : -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see task_scheduler.TaskSchedule#scheduledDuration()
	 */
	@Override
	public long scheduledDuration() {
		return durationSeconds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see task_scheduler.TaskSchedule#scheduledEndTime()
	 */
	@Override
	public int scheduledEndTime() {
		return endTimeSeconds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see task_scheduler.TaskSchedule#scheduledStartTime()
	 */
	@Override
	public int scheduledStartTime() {
		return startTimeSeconds;
	}

//	/*
//	 *  Builds a scheduled time. Using the consumer functional interface.
//	 */
//	public static class ScheduledTimeBuilder {
//
//		public long startTimeSeconds = 0;				// Task's start time. Value 1 - 86400.
//		public long endTimeSeconds = 0;					// Task's end time. Value 1 - 86400.
//		public long durationSeconds = 0;				// Duration (end - start).
//
//		public MutableTime startTimeFormatter = null;	// Start time as a formatted string.
//		public MutableTime endTimeFormatter = null;		// End time as a formatted string.
//
//		/*
//		 *  Builder function with Consumer.  
//		 */
//		public ScheduledTimeBuilder with(Consumer<ScheduledTimeBuilder> builderFunction, int startTimeSeconds, int endTimeSeconds) {
//			builderFunction.accept(this);
//			return this;
//		}
//
//		/*
//		 *  Build the Scheduled time.
//		 */
////		public ScheduledTime build() {
////			return new ScheduledTime(this);
////		}
//	}

}
