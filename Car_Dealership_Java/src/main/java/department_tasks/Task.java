/**
 * 
 */
package department_tasks;

import department.DepartmentTasks;

/**
 * @author Steve Brown
 *
 */
public class Task  {

	private DepartmentTasks dTask = null;	
		
	public Task(DepartmentTasks dTask) {
		super();
		this.dTask = dTask;
	}

	public DepartmentTasks departmentTask() {
		return dTask;
	}
	
	public void departmentTask(DepartmentTasks t) {
		this.dTask = t;
	}
	
	public void run() {
		this.dTask.runTask();	
	}
	
}
