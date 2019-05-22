package tasks.lists;

import people.employees.Employee;
import tasks.abstract_tasks.Task;

/**
 * 
 * @author Steve Brown
 *
 */
public interface ManagementTaskList {
	
	// Add a task to the assigned task map.
	void addTask(Employee e, Task t);
	
	// See if a task is in the assigned map.
	boolean alreadyAssignedTask(Task t);
	
	// Once the task has been executed remove it from the map.
	void removeAssignedTask(Employee e);
}
