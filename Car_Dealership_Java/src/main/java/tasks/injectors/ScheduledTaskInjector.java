package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.ScheduledTask;
import tasks.details.TaskSchedule;

/**
 * @author Steve Brown
 *
 *  Injects a new scheduled task for a department.
 *  Only departments can schedule tasks.
 *   	- See drawing TaskIoD.uxf.
 */
public interface ScheduledTaskInjector {
	ScheduledTask getNewTask(Department tasksDepartment, TaskSchedule tasksSchedule);
}
