package tasks.details;

import people.employees.Employee;
import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 *
 */
public interface ManagersTaskReport {

	Employee getEmployee();

	Task getTask();

	boolean isTaskComplete();

	long getCompletedAt();

	@Override
	String toString();
}