package tasks.task_details;

import people.employees.Employee;
import tasks.task_super_objects.Task;

public interface ManagerReport {

	Employee getEmployee();

	Task getTask();

	boolean isTaskComplete();

	long getCompletedAt();

	@Override
	String toString();
}