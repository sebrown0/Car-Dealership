package tasks.task_objects;

import tasks.task_creators.AtomicTasksDetails;

/**
 * @author Steve Brown
 *
 *	An Atomic task that doesn't belong to a department.
 */
public class OpenDealership extends AtomicTask {

	/**
	 * @param tasksDetails: Details for this atomic task.
	 */
	public OpenDealership(AtomicTasksDetails tasksDetails) {
		super(tasksDetails);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		tasksDetails.getLog().logEntry(tasksDetails.getObjId(), "Executing: Opening Dealer");
	}
}
