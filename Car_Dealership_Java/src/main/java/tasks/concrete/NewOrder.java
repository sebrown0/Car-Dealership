package tasks.concrete;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 *	An Atomic, Departmental task.
 */
public class NewOrder extends AtomicTask{

	public NewOrder(Department tasksDepartment) {
		super(tasksDepartment);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
//		tasksDetails.getLog().logEntry(tasksDetails.getTaskID(), "Executing: New Order");
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
}
