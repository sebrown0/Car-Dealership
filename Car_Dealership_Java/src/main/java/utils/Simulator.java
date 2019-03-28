/**
 * 
 */
package utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import department_tasks.Task_OLD;

/**
 * @author Steve Brown
 *
 * Simulates the day-to-day operation of the Car Dealership.
 */
public class Simulator {

	private static Log log = new Logger(false);
	private final static String objId = "<Simulator>";
		
	public static void start() {
	
		log.logEntry(objId, "Start");
		
//		StockDept stockDept = new StockDept();
//		SalesDept salesDept = new SalesDept();
		
		// Use BlockingQueue - thread safe and handles wait & notify for us.
		BlockingQueue<Task_OLD> taskQueue = new ArrayBlockingQueue<Task_OLD>(5);
		
//		Thread taskProducer = new Thread(new ContinuousTasksProducer(taskQueue, salesDept, stockDept));
		Thread taskConsumer = new Thread(new ContinuousTasksConsumer(taskQueue));
		
//		taskProducer.start();
		taskConsumer.start();
		
	}
}
