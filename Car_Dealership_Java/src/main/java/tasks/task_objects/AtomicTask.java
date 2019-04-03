package tasks.task_objects;

import tasks.task_creators.AtomicTaskRunner;
import tasks.task_creators.AtomicTasksDetails;

/**
 * @author Steve Brown
 *
 * 	Super class for an atomic task object.
 * 	Any atomic task should extend this.
 */
public abstract class AtomicTask implements AtomicTaskRunner {

	protected AtomicTasksDetails tasksDetails;

	public AtomicTask(AtomicTasksDetails tasksDetails) {
		this.tasksDetails = tasksDetails;
		setTasksObjectID(this.tasksDetails);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskRunner#tasksDetails()
	 */
	@Override
	public AtomicTasksDetails tasksDetails() {
		return tasksDetails;
	}

}
