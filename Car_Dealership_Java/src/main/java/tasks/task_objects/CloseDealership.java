package tasks.task_objects;

import tasks.task_creators.AtomicTasksDetails;

/**
 * @author Steve Brown
 *
 * 	An Atomic task that doesn't belong to a department.
 */
public class CloseDealership extends AtomicTask {

	/**
	 * @param tasksDetails: Details for this atomic task.
	 */
	public CloseDealership(AtomicTasksDetails tasksDetails) {
		super(tasksDetails);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		tasksDetails.getLog().logEntry(tasksDetails.getObjId(), "Executing: Closing Dealer");
	}

}
