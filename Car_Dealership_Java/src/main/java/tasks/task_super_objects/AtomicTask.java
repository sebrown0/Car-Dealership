package tasks.task_super_objects;

import departments.department.Department;
import tasks.task_details.TasksDetails;

public abstract class AtomicTask extends Task {
	public AtomicTask(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
	} 
}

