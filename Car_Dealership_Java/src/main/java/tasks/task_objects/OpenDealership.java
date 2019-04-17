package tasks.task_objects;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *	An Atomic task that doesn't belong to a department.
 */
public class OpenDealership extends AtomicTask {
	
	public OpenDealership(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
	}

	/*
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return OpenDealership.class.getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
//		tasksDetails.getLog().logEntry(tasksDetails.getTaskID(), "Executing: Opening Dealer");
//		tasksDetails.getDealerDAO().getTimer().setObserver(this);
	}
}
