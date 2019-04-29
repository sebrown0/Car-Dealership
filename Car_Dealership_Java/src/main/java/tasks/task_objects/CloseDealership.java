package tasks.task_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 * 	An Atomic task that doesn't belong to a department.
 */
public class CloseDealership extends AtomicTask {
	
	public CloseDealership(Department tasksDepartment) {
		super(tasksDepartment);
	}
	
	@Override
	public void executeTask() {
		// THIS WILL BE IN THE EMPLOYEE
//		tasksDepartment.log().logEntry(tasksDetails.getTaskID(), "Executing: Closing Dealer");
		System.out.println("Executing: Closing Dealer");
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.addTask(this);
	}
}
