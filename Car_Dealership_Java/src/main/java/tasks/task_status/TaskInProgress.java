/**
 * 
 */
package tasks.task_status;

/**
 * @author Steve Brown
 *
 *  Task's status is in progress (0).
 */
public class TaskInProgress extends TaskStatus {
	
	/**
	 * @param taskID: see super.
	 */
	public TaskInProgress(String taskID) {
		super(taskID);
	}

	/*
	 * return 0 to indicate that the task is in progress.
	 */
	@Override
	public int taskStatus() {
		return 0;
	}

}
