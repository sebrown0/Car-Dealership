package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.ScheduledTask;
import tasks.concrete.ScheduledTest2;
import tasks.details.TaskSchedule;

/**
 * @author Steve Brown
 *
 */
public class ScheduledInjectorTest2 implements ScheduledTaskInjector {

	@Override
	public ScheduledTask getNewTask(Department tasksDepartment, TaskSchedule tasksSchedule) {
		return new ScheduledTest2(tasksDepartment, tasksSchedule);
	}
}
