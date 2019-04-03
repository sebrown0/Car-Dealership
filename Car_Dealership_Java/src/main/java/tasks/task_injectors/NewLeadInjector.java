package tasks.task_injectors;

import tasks.task_creators.DepartmentTaskInjector;
import tasks.task_creators.DepartmentTasksDetails;
import tasks.task_creators.Task;
import tasks.task_creators.TaskConsumer;
import tasks.task_objects.NewLead;

/**
 * @author Steve Brown
 *
 *  Return a new task NewLead. 
 *  To be executed immediately.
 */
public class NewLeadInjector implements DepartmentTaskInjector {

//	public TaskConsumer getNewTask(Department department) {
//		// TODO - Handle cast exception is wrong department is passed.
//		return new Task(new NewLead((SalesDepartment) department, TypeOfTask.ATOMIC));
//	}

	@Override
	public TaskConsumer getNewTask(DepartmentTasksDetails taskDetails) {
		return new Task(new NewLead(taskDetails));
	}
}
