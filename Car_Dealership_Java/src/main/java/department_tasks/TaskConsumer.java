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
	
	BlockingQueue<Task_OLD> taskQueue;
	
	public TaskConsumer(BlockingQueue<Task_OLD> taskQueue) {	
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				
				Task_OLD task = taskQueue.take();
				log.logEntry(objId, "Executing task " + task.getClass().getSimpleName());
//				task.run();
				startTask(task);
				
				log.logEntry(objId, "Finished task " + task.getClass().getSimpleName());


				
				Thread.sleep(900);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void startTask(Task_OLD task) {
		log.logEntry(objId, "Inside task " + task.getClass().getSimpleName());
		Thread taskThread = new Thread(task);
		taskThread.start();
		try {
			if(task.blocking()) {
				taskThread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();					// TODO - Errorhandler
		}
	}
}

