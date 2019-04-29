package tasks.task_super_objects;

import departments.department.Department;

public abstract class AtomicTask extends Task {
	public AtomicTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

