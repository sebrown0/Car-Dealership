package tasks.task_objects;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 * 	An Atomic task that doesn't belong to a department.
 */
public class CloseDealership extends AtomicTask {
	
	public CloseDealership(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
	}

	/*
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return CloseDealership.class.getSimpleName();
	}
	
	
	@Override
	public void executeTask() {
		// THIS WILL BE IN THE EMPLOYEE
//		tasksDetails.getLog().logEntry(tasksDetails.getTaskID(), "Executing: Closing Dealer");
		System.out.println("Executing: Closing Dealer");
	}

}
