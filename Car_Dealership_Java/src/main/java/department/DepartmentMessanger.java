/**
 * 
 */
package department;

import java.util.concurrent.BlockingQueue;

/**
 * @author Steve Brown
 *
 *	Passes messages or tasks between departments.
 */
public class DepartmentMessanger {
//	BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<Task>(5);
	BlockingQueue<Department> departments = null;

	public DepartmentMessanger(BlockingQueue<Department> departments) {
		super();
		this.departments = departments;
	}
	
	public BlockingQueue<Department> getAllDepartments() {
		return departments;
	}
	
	/*
	 * Return a department that matches the department name.
	 * TODO - Make this more robust.
	 */
	public Department getDepartment(String deptName) {
		
		for (Department d : departments) {
			if(d.deptName().compareTo(deptName) == 0)
				return d;
		}
		
		return null;
	}
}
