package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TaskSchedule;
import tasks.task_objects.ScheduledTest2;
import tasks.task_super_objects.ScheduledTask;

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
