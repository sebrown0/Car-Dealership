package tasks.details;

import people.employees.Employee;
import tasks.abstract_tasks.Task;

public interface ManagerReport {

	Employee getEmployee();

	Task getTask();

	boolean isTaskComplete();

	long getCompletedAt();

	@Override
	String toString();
}