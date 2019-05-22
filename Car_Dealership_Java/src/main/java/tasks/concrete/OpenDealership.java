package tasks.concrete;

import dealer_management.DealerDAO;
import departments.department.Department;
import tasks.abstract_tasks.ManagementTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 *	A management task that can be performed by a manager 
 *	or an employee of a department.
 */
public class OpenDealership extends ManagementTask {
	
	// Use for a management task.
	public OpenDealership(DealerDAO dealerDAO) {
		super(null);
		this.log = dealerDAO.getLog();
	}

	// Use for an atomic task.
	public OpenDealership(Department tasksDepartment) {
		super(tasksDepartment);
		this.log = tasksDepartment.log();
	}

	@Override
	public void executeTask() {
		log.logEntry(this, "Executing: Opening Dealer");
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}

}
