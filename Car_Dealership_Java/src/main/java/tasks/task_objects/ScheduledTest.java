package tasks.task_objects;

import departments.department.Department;
import task_strategy.TaskListVisitor;
import tasks.task_details.TaskSchedule;
import tasks.task_super_objects.ScheduledTask;

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
		taskList.addTask(this);
	}

	@Override
	public int getStartTime() {
		return getTaskStartTime();
	}

}
