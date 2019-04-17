/**
 * 
 */
package tasks.task_reciever;

import tasks.task_super_objects.AtomicTask;
import tasks.task_super_objects.ScheduledTask;

/**
 * @author Brown
 *
 */
public interface TaskReceiver {
	void getNewTask(AtomicTask task);
	void getNewTask(ScheduledTask task);	
}
