package tasks.task_injectors;

import departments.department.Department;
import tasks.task_creators.AtomicTaskInjector;
import tasks.task_creators.DepartmentTaskInjector;
import tasks.task_creators.DepartmentTasksDetails;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import tasks.task_creators.TypeOfTask;
import tasks.task_objects.RollCall;

/**
 * @author Steve Brown
 *
 *
 *  Return a new task RollCall for a Department. 
 * 	To be executed immediately.
 */
public class RollCallInjector implements DepartmentTaskInjector {

	/*
	 * (non-Javadoc)
	 * @see tasks.DepartmentTaskInjector#getNewTask(tasks.DepartmentTasksDetails)
	 */
	@Override
	public TaskConsumer getNewTask(DepartmentTasksDetails tasksDetails) {
		return new Task(new RollCall(tasksDetails));
	}
}
