package tasks.task_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *	An Atomic task that doesn't belong to a department.
 */
public class OpenDealership extends AtomicTask {
	
	public OpenDealership(Department tasksDepartment) {
		super(tasksDepartment);
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.AtomicTaskRunner#executeTask()
	 */
	@Override
	public void executeTask() {
//		tasksDepartment.log().logEntry(tasksDetails.getTaskID(), "Executing: Opening Dealer");
		System.out.println("Executing: Opening Dealer");
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.addTask(this);
	}
}
