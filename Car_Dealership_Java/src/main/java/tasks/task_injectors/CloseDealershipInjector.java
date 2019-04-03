package tasks.task_injectors;

import tasks.task_creators.AtomicTaskInjector;
import tasks.task_creators.AtomicTasksDetails;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import tasks.task_objects.CloseDealership;

/**
 * @author Steve Brown
 *
 *  Return a new task CloseDealership for NO Department. 
 *  To be executed immediately.
 */
public class CloseDealershipInjector implements AtomicTaskInjector {

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskInjector#getNewTask(tasks.AtomicTasksDetails)
	 */
	@Override
	public TaskConsumer getNewTask(AtomicTasksDetails taskDetails) {
		return new Task(new CloseDealership(taskDetails));
	}

}
