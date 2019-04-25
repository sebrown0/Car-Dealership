package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TaskSchedule;
import tasks.task_details.TasksDetails;
import tasks.task_objects.ScheduledTest;
import tasks.task_super_objects.ScheduledTask;

/**
 * @author Steve Brown
 *
 */
public class ScheduledInjectorTest implements ScheduledTaskInjector {

	@Override
	public ScheduledTask getNewTask(TasksDetails taskDetails, Department tasksDepartment, TaskSchedule tasksSchedule) {
		return new ScheduledTest(taskDetails, tasksDepartment, tasksSchedule);
	}
}
