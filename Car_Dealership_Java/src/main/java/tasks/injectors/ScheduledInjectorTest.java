package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.ScheduledTask;
import tasks.concrete.ScheduledTest;
import tasks.details.TaskSchedule;

/**
 * @author Steve Brown
 *
 */
public class ScheduledInjectorTest implements ScheduledTaskInjector {

	@Override
	public ScheduledTask getNewTask(Department tasksDepartment, TaskSchedule tasksSchedule) {
		return new ScheduledTest(tasksDepartment, tasksSchedule);
	}
}
