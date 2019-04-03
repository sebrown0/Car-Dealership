package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *  Provides a method for getting the task's department.
 */
public interface DepartmentTaskRunner extends AtomicTaskRunner{

	/*
	 *  Retreive all the details for a task.
	 */
	DepartmentTasksDetails tasksDetails();
	
	/*
	 *  Set the Task's object ID to Department.
	 */
	default void setTasksObjectID(DepartmentTasksDetails tasksDetails) {
		tasksDetails.setObjId("<Department Task>" + " <" + this.getClass().getSimpleName() + ">");
	}
}
