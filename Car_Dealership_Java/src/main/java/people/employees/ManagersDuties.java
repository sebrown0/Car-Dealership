/**
 * 
 */
package people.employees;

import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 *
 */
public interface ManagersDuties {
	void delegateTask(Task t);
	void performTask(Task t);
}
