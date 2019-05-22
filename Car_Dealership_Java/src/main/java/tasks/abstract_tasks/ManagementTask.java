package tasks.abstract_tasks;

import departments.department.Department;
import utils.logger.Log;

public abstract class ManagementTask extends Task {
	
	protected Log log;
	
	public ManagementTask(Department tasksDepartment) {
		super(tasksDepartment);
	} 
}

