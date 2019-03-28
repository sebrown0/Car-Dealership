/**
 * 
 */
package application;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import department.Department;
import department_tasks.CreateDepartments;
import department_tasks.DepartmentTask;
import department_tasks.TaskRollCall;
import employees.Caretaker;
import employees.Employee;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 *	Tasks to be run at the beginning of every new day.
 *
 *		1. New log for this day.
 *		2. Create all departments.
 *		3. Perform a roll call for all departments.
 *		4. Assign a messenger to each department.
 */
public class Day {

	private Log log = new Logger(false);
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	private BlockingQueue<Department> departments = null;
	private Employee caretaker = new Caretaker();
	
	/*
	 *  Start the process.
	 */
	public void begin() {
		
		log.logEntry("<Day>", "Starting a new day.");
		
		departments = createDepartments();
		rollCall(departments);
		
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();					// TODO - Errorhandler
		}
		
		executor.shutdown();
	}
	
	/*
	 *  Run the task with a new thread. Block if it's a blocking task.
	 */
//	private void runTask(Task_OLD task) {
//			
//		Thread taskThread = new Thread(task);
//		taskThread.start();
//		try {
//			if(task.blocking()) {
//				taskThread.join();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();					// TODO - Errorhandler
//		}
//	}
	
	/*
	 *  Create all departments that are in the TableNames.DEPT table. 
	 */
	private BlockingQueue<Department> createDepartments() {

		CreateDepartments depts = new CreateDepartments();
		depts.createDepartments();
		
		return depts.getDepartments();
	
	}
	
	/*
	 *  Perform a roll call on all the departments, i.e. check who is available for work.
	 */
	private void rollCall(BlockingQueue<Department> departments) {
		
		if(!departments.isEmpty()) {
			for(Department d: departments) {
				d.receiveTask(new TaskRollCall(d), caretaker);
				// Perform the task as soon as we have it.
				caretaker.performTask(); 
			}
		}
	}
	
	/*
	 *  Return the newly created departments.
	 */
	public BlockingQueue<Department> getDepartments(){
		return departments;
	}
	
}
