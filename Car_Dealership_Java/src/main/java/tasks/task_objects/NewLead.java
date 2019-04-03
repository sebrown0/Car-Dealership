package tasks.task_objects;

import tasks.task_creators.DepartmentTasksDetails;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewLead extends DepartmentTask {

	/**
	 * @param tasksDetails: Details for this departmental task.
	 */	
	public NewLead(DepartmentTasksDetails tasksDetails) {
		super(tasksDetails);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		tasksDetails.getLog().logEntry(tasksDetails.getObjId(), "Executing: New Lead");
	}

}
