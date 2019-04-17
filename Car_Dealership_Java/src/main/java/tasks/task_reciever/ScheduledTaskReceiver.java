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
public class ScheduledTaskReceiver extends Receiver implements TaskReceiver {
	
//	@Override
//	public void getNewTask(TaskRunner task) {
//		System.out.println("Adding new Sceduled");
//		scheduledTasksReceived.add((ScheduledTaskRunner) task);
//	}

	@Override
	public void getNewTask(AtomicTask task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNewTask(ScheduledTask task) {
		// TODO Auto-generated method stub
		
	}
}
