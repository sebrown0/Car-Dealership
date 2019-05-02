package tasks.task_super_objects;

import departments.department.Department;
import utils.logger.Log;

public abstract class ManagementTask extends Task {
	
	protected Log log;
	
	public ManagementTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

