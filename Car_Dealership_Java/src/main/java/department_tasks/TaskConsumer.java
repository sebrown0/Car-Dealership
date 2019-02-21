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
 */
public class TaskConsumer implements Runnable{

	private static Log log = new Logger(false);
	private final static String objId = "<Simulator> <Consumer>";
	
	BlockingQueue<Task> taskQueue;
	
	public TaskConsumer(BlockingQueue<Task> taskQueue) {	
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
		
//		while(true) {
			try {
				
				Task task = taskQueue.take();
				log.logEntry(objId, "Executing task -> " + task.getClass().getSimpleName());
				task.run();
				
				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
}
