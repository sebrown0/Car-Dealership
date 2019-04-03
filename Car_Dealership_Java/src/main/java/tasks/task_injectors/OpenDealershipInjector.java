package tasks.task_injectors;

import tasks.task_creators.AtomicTaskInjector;
import tasks.task_creators.AtomicTasksDetails;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import tasks.task_objects.OpenDealership;

/**
 * @author Steve Brown
 *
 *  Return a new task OpenDealership for NO Department. 
 *  To be executed immediately.
 */
public class OpenDealershipInjector implements AtomicTaskInjector {

	@Override
	public TaskConsumer getNewTask(AtomicTasksDetails taskDetails) {
		return new Task(new OpenDealership(taskDetails));
	}

}
