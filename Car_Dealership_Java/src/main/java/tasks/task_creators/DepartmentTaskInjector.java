package tasks.task_creators;

/**
 * @author Steve Brown
 *
 *  Injects a new Atomic task for a department, i.e. a task that is to be run immediately.
 *   	- See drawing TaskIoD.uxf.
 */
public interface DepartmentTaskInjector {

	/*
	 * A new departmental task without a schedule.
	 */
	TaskConsumer getNewTask(DepartmentTasksDetails taskDetails);
}
