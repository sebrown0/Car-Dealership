/**
 * 
 */
package tasks.task_status;

/**
 * @author Steve Brown
 *
 *  Keeps track of a task's status.
 *  
 *  This class cannot be instantiated so a task's status must be
 *  one of the following:
 *  	Failed = -1
 *  	InProgress = 0
 *  	Complete = 1
 */
public abstract class TaskStatus implements TasksStatus {

	private String taskID;

	/**
	 * @param taskID: the ID of the task (Task Object.getClass().getSimpleName()) 
	 */
	public TaskStatus(String taskID) {
		this.taskID = taskID;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.task_actions.TaskStatus#taskID()
	 */
	@Override
	public String taskID() {
		return taskID;
	}

}
