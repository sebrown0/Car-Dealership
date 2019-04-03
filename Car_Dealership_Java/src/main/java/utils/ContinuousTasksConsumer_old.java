/**
 * 
 */
package utils;

import java.util.concurrent.BlockingQueue;

import department_tasks.Task_OLD;

/**
 * @author Steve Brown
 *
 */
public class ContinuousTasksConsumer_old implements Runnable{

//	private static Log log = new Logger(false);
	private final static String objId = "<Simulator> <Consumer>";
	
	BlockingQueue<Task_OLD> taskQueue;
	
	public ContinuousTasksConsumer_old(BlockingQueue<Task_OLD> taskQueue) {	
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
//		TaskRunner taskRunner = new TaskRunner(); TODO - R
		
		while(true) {
			try {
				Task_OLD task = taskQueue.take(); 
//				log.logEntry(objId, "Executing task -> " + task.getClass().getName());
				task.run();
				
				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
