/**
 * 
 */
package department_tasks;

import java.util.concurrent.BlockingQueue;

import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 * A producer of tasks.
 */
public class TaskProducer implements Runnable {

	private static Log log;
	private final static String objId = "<Producer>";
	
	private BlockingQueue<Task_OLD> taskQueue = null;
	private Task_OLD task = null;

	public TaskProducer(BlockingQueue<Task_OLD> taskQueue) {	
		this.taskQueue = taskQueue;
	}
	
//	public TaskProducer(BlockingQueue<Task> taskQueue, Task task) {	
//		this.taskQueue = taskQueue;
//		this.task = task;
//	}
	
	public void addTask(Task_OLD task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		try {
			synchronized (this) {
			
				log.logEntry(objId, "Adding " + task.getClass().getSimpleName() + " to the thread pool");
				taskQueue.put(task);
							
				Thread.sleep(500); 	// Amount of time to wait before looking for a new task.
			}
		} catch (InterruptedException e) {
			e.printStackTrace();	// TODO - Error handler
		}
	}	
}
