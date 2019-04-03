package tasks.task_injectors;

import tasks.task_creators.DepartmentTaskInjector;
import tasks.task_creators.DepartmentTasksDetails;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import tasks.task_objects.NewOrder;

/**
 * @author Steve Brown
 *
 *  Return a new task - NewOrder. 
 *  To be executed immediately.
 */
public class NewOrderInjector implements DepartmentTaskInjector {

//	public TaskConsumer getNewTask(Department department) {
//		return new Task(new NewOrder((OrderDepartment) department, TypeOfTask.ATOMIC));
//	}

	/*
	 * (non-Javadoc)
	 * @see tasks.DepartmentTaskInjector#getNewTask(tasks.DepartmentTasksDetails)
	 */
	@Override
	public TaskConsumer getNewTask(DepartmentTasksDetails taskDetails) {
		return new Task(new NewOrder(taskDetails));
	}
}
