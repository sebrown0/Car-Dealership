package tasks.concrete;

import departments.department.Department;
import tasks.abstract_tasks.ScheduledTask;
import tasks.details.TaskSchedule;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 * 	An Atomic task that doesn't belong to a department.
 */
public class ScheduledTest extends ScheduledTask {
	
	public ScheduledTest(Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDepartment, tasksSchedule);
	}
	
	@Override
	public void executeTask() {
		getTasksDepartment().log().logEntry(this, "EXECUTING SCHEDULED TEST ");
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}

	@Override
	public int getStartTime() {
		return getTaskStartTime();
	}

}
