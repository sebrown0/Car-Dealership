/**
 * 
 */
package tasks.task_status;

/**
 * @author Steve Brown
 *
 *  Task's status is complete (1).
 */
public class TaskComplete extends TaskStatus {
	
	/**
	 * @param taskID: see super.
	 */
	public TaskComplete(String taskID) {
		super(taskID);
	}

	/*
	 * return 1 to indicate that the task is complete.
	 */
	@Override
	public int taskStatus() {
		return 1;
	}

}
