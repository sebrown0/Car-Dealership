package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.ScheduledTask;
import tasks.concrete.UpdateStock;
import tasks.details.TaskSchedule;

/**
 * @author Steve Brown
 *
 */
public class UpdateStockInjector implements ScheduledTaskInjector {

	@Override
	public ScheduledTask getNewTask(Department tasksDepartment, TaskSchedule tasksSchedule) {
		return new UpdateStock(tasksDepartment, tasksSchedule);
	}
}
