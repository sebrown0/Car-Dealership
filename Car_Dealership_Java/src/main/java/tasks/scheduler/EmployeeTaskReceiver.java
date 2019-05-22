package tasks.scheduler;

import people.employees.Manager;
import tasks.abstract_tasks.Task;

public interface EmployeeTaskReceiver {
	<T extends Task> void accept(T t, Manager manager);
}
