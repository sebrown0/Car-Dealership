package tasks.task_creators;

/**
 * @author Steve Brown
 *
 * 	Get a tasks schedule.
 *  Only departments can schedule tasks.
 */
public interface ScheduledTaskRunner extends DepartmentTaskRunner {

	/*
	 *  Retreive all the details for a task.
	 */
	ScheduledTasksDetails tasksDetails();
	
	/*
	 *  Set the Task's object ID to Scheduled.
	 */
	default void setTasksObjectID(ScheduledTasksDetails tasksDetails) {
		tasksDetails.setObjId("<Scheduled Task>" + " <" + this.getClass().getSimpleName() + ">");
	}
}
