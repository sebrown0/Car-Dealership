/**
 * 
 */
package department_tasks;

/**
 * @author Steve Brown
 *
 *	Create a reference to a specific Task Instance.
 */
public interface Task_OLD extends Runnable {
	
	@Override
	void run();						// Runs the task that is THIS task.
	
	boolean blocking();				// Is it a blocking task.	
	void add();						// Add the task that is THIS task to the department's task list.
}
