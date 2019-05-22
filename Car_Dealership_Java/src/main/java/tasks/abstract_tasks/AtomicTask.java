package tasks.abstract_tasks;

import departments.department.Department;

public abstract class AtomicTask extends Task {
	public AtomicTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

