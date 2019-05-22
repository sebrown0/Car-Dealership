package tasks.concrete;

import departments.department.Department;
import tasks.abstract_tasks.Task;

public abstract class AtomicTask extends Task {
	public AtomicTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

