package tasks.task_super_objects;

/**
 * @author Steve Brown
 *
 *
 * 	Provides a method for running the task.
 */
public interface TaskRunner extends Runnable {

	void executeTask();
	
	@Override
	default void run() {
		executeTask();
	}
}
