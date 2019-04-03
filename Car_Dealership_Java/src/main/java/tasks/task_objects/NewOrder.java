package tasks.task_objects;

import tasks.task_creators.DepartmentTasksDetails;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewOrder extends DepartmentTask{

	/**
	 * @param tasksDetails: Details for this departmental task.
	 */	
	public NewOrder(DepartmentTasksDetails tasksDetails) {
		super(tasksDetails);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		tasksDetails.getLog().logEntry(tasksDetails.getObjId(), "Executing: New Order");
	}
}
