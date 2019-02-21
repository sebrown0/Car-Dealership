/**
 * 
 */
package utils;

import java.util.concurrent.BlockingQueue;

import department_tasks.Task;

/**
 * @author Steve Brown
 *
 */
public class ContinuousTasksConsumer implements Runnable{

	private static Log log = new Logger(false);
	private final static String objId = "<Simulator> <Consumer>";
	
	BlockingQueue<Task> taskQueue;
	
	public ContinuousTasksConsumer(BlockingQueue<Task> taskQueue) {	
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
//		TaskRunner taskRunner = new TaskRunner(); TODO - R
		
		while(true) {
			try {
				Task task = taskQueue.take(); 
				log.logEntry(objId, "Executing task -> " + task.getClass().getName());
				task.run();
				
				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
