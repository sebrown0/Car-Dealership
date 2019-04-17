package tasks.task_objects;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewLead extends AtomicTask {
	
	public NewLead(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
	}

	/*
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return NewLead.class.getSimpleName();
	}
	
	/*
	 * (non-Javadoc)
	 * @see tasks.TaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
//		tasksDetails.getLog().logEntry(tasksDetails.getTaskID(), "Executing: New Lead");
		System.out.println("Executing: New Lead");
	}

}
