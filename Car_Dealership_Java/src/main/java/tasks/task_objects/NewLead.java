package tasks.task_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewLead extends AtomicTask {
	
	public NewLead(Department tasksDepartment) {
		super(tasksDepartment);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
		tasksDepartment.log().logEntry(this, "Executing: New Lead");
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
}
