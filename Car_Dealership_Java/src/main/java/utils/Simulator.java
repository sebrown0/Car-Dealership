/**
 * 
 */
package utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import department_tasks.Task;

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
		BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<Task>(5);
		
//		Thread taskProducer = new Thread(new ContinuousTasksProducer(taskQueue, salesDept, stockDept));
		Thread taskConsumer = new Thread(new ContinuousTasksConsumer(taskQueue));
		
//		taskProducer.start();
		taskConsumer.start();
		
	}
}
