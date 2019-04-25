package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TaskSchedule;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.ScheduledTask;

/**
 * @author Steve Brown
 *
 *  Injects a new scheduled task for a department.
 *  Only departments can schedule tasks.
 *   	- See drawing TaskIoD.uxf.
 */
public interface ScheduledTaskInjector {
	ScheduledTask getNewTask(TasksDetails taskDetails, Department tasksDepartment, TaskSchedule tasksSchedule);
}
