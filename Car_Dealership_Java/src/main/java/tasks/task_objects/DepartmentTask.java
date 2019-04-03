package tasks.task_objects;

import departments.department.Department;
import tasks.task_creators.DepartmentTaskRunner;
import tasks.task_creators.DepartmentTasksDetails;

/**
 * @author Steve Brown
 *
 *         TODO
 */
public abstract class DepartmentTask implements DepartmentTaskRunner{

	protected Department tasksDepartment = null;
	protected DepartmentTasksDetails tasksDetails;

	public DepartmentTask(DepartmentTasksDetails tasksDetails) {
		this.tasksDepartment = tasksDetails.getDepartment();
		this.tasksDetails = tasksDetails;
		this.tasksDetails.setDepartment(tasksDetails.getDepartment());
		setTasksObjectID(this.tasksDetails);
	}

	@Override
	public DepartmentTasksDetails tasksDetails() {
		return tasksDetails;
	}

}
