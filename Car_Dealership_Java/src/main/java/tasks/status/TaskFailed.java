/**
 * 
 */
package tasks.status;

/**
 * @author Steve Brown
 *
 *  Task's status is failed (-1).
 */
public class TaskFailed extends TaskStatus {
	
	/**
	 * @param taskID: see super.
	 */
	public TaskFailed(String taskID) {
		super(taskID);
	}

	/*
	 * return -1 to indicate that the task failed.
	 */
	@Override
	public int taskStatus() {
		return -1;
	}

}
