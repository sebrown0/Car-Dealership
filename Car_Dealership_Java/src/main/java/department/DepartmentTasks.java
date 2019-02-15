/**
 * 
 */
package department;

import department_tasks.TaskRunner;

/**
 * @author Brown
 *
 */
public abstract class DepartmentTasks implements TaskRunner {

	private Department dept = null;

	public DepartmentTasks(Department dept) {
		super();
		this.dept = dept;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
//	public void deptTask() {
//		System.out.println("Dept task");
//	}
	
}
