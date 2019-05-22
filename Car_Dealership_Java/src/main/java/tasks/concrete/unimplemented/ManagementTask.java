package tasks.concrete;

import departments.department.Department;
import tasks.abstract_tasks.Task;
import utils.logger.Log;

public abstract class ManagementTask extends Task {
	
	protected Log log;
	
	public ManagementTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

