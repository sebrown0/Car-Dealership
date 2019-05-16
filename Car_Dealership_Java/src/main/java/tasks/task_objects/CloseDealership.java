package tasks.task_objects;

import dealer_management.DealerDAO;
import departments.department.Department;
import task_strategy.TaskListVisitor;
import tasks.task_super_objects.ManagementTask;

/**
 * @author Steve Brown
 *
 *	A management task that can be performed by a manager 
 *	or an employee of a department.
 */
public class CloseDealership extends ManagementTask {
	
	// Use for a management task.
	public CloseDealership(DealerDAO dealerDAO) {
		super(null);
		this.log = dealerDAO.getLog();
	}
	
	// Use for an atomic task.
	public CloseDealership(Department tasksDepartment) {
		super(tasksDepartment);
	}
	
	@Override
	public void executeTask() {
		log.logEntry(this, "Executing: Closing Dealer");
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
}
