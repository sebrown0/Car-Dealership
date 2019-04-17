package tasks.task_injectors;

import departments.department.Department;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

/**
 * @author Steve Brown
 *
 *  Injects a new Atomic task, i.e. a task that is to be run immediately.
 *   	- See drawing TaskIoD.uxf.
 */
public interface AtomicTaskInjector {
	AtomicTask getNewTask(TasksDetails taskDetails, Department tasksDepartment);	
}
