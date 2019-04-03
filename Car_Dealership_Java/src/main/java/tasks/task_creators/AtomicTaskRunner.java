package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *	Super interface for a Task Runner. 
 *	Department Task Runner and Scheduled Task Runner are derived from this.
 *
 * 	Provides a method for running the task and getting the task's details.
 */
public interface AtomicTaskRunner {

	/*
	 * Execute the task.
	 */
	void executeTask();
	
	/*
	 *  Retreive all the details for a task.
	 */
	AtomicTasksDetails tasksDetails();
	
	/*
	 *  Set the Task's object ID to Atomic.
	 */
	default void setTasksObjectID(AtomicTasksDetails tasksDetails) {
		tasksDetails.setObjId("<Atomic Task>" + " <" + this.getClass().getSimpleName() + ">");
	}
}
