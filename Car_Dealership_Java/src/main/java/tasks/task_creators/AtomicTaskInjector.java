package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *  Injects a new Atomic task, i.e. a task that is to be run immediately.
 *   	- See drawing TaskIoD.uxf.
 */
public interface AtomicTaskInjector {

	/*
	 * A new ATOMIC task.
	 */
	TaskConsumer getNewTask(AtomicTasksDetails taskDetails);
	
}
