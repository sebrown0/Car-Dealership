package tasks.concrete;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewLead extends AtomicTask {
	
	public NewLead(Department tasksDepartment) {
		super(tasksDepartment);
	}

	@Override
	public void executeTask() {
		tasksDepartment.log().logEntry(this, "Executing: New Lead");
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
}
