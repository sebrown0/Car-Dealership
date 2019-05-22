package tasks.injectors;

import departments.department.Department;
import tasks.abstract_tasks.AtomicTask;

/**
 * @author Steve Brown
 *
 *  Injects a new Atomic task, i.e. a task that is to be run immediately.
 *   	- See drawing TaskIoD.uxf.
 */
public interface AtomicTaskInjector {
	AtomicTask getNewTask(Department tasksDepartment);	
}
