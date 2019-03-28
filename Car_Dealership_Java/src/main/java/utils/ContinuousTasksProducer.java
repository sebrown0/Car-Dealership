/**
 * 
 */
package utils;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import department.Department;
import department_tasks.MeetCustomer;
import department_tasks.Task_OLD;
import department_tasks.TaskUpdateStock;

/**
 * @author Steve Brown
 *
 * A producer of tasks.
 */
public class ContinuousTasksProducer implements Runnable {

	private static Log log = new Logger(false);
	private final static String objId = "<Simulator> <Producer>";
	
	private final static String [] continuousTasks = {"<None>", "<NewLeadTask>", "<StockCheckTask>"};
	BlockingQueue<Task_OLD> taskQueue;
	
	private Department salesDept;
	private Department stockDept;
		
	// TODO - Pass departments. Each department has its own BlockingQueue.
	public ContinuousTasksProducer(BlockingQueue<Task_OLD> taskQueue, Department saleDept, Department stockDept) {	
		this.taskQueue = taskQueue;
		this.salesDept = saleDept;
		this.stockDept = stockDept;
	}
	
	@Override
	public void run() {
		Random rand = new Random();

		while (true) {
			try {
				synchronized (this) {
					int dice = rand.nextInt(continuousTasks.length);
					
					switch (continuousTasks[dice]) {
					case "<NewLeadTask>":
						log.logEntry(objId, "New Lead");
						taskQueue.put(new MeetCustomer(salesDept));
						break;
						
					case "<StockCheckTask>":
						log.logEntry(objId, "Stock Check");
						taskQueue.put(new TaskUpdateStock(stockDept));
						break;
					
					default:
						break;			// Don't have a task so do nothing.
					}
					
					Thread.sleep(500); 	// Amount of time to wait before looking for a new task.
				}
			} catch (InterruptedException e) {
				e.printStackTrace();	// TODO - Error handler
			}
		}
	}	
}
