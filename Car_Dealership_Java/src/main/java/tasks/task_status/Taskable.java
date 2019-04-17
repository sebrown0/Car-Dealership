/**
 * 
 */
package tasks.task_status;

/**
 * @author Steve Brown
 *
 *  Pass a message back to the task's parent/calling object with
 *  the task's status.
 */
public interface Taskable {

	/*
	 *  Give the parent the status.
	 */
	void taskUpdate(TasksStatus tasksStatus);
}
