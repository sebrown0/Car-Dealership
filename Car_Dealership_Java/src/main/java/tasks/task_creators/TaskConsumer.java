package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *	
 */
public interface TaskConsumer extends Runnable {

	/*
	 *  Assign a task to a department.
	 */
	void assignTask(String msg); // Give it to department

	/*
	 *  Get the task.
	 */
	AtomicTaskRunner getTask(); // TODO - This will have to change to return the correct interface.

}
