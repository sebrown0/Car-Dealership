/**
 * 
 */
package departments.department;

import java.util.concurrent.BlockingQueue;

/**
 * @author Steve Brown
 *
 *	A list of all the departments.
 *	Passes messages or tasks between departments.
 */
public class DepartmentMessenger implements Messenger {
	BlockingQueue<Department> departments = null;
	
	public DepartmentMessenger(BlockingQueue<Department> departments) {		
		this.departments = departments;
	}

	@Override
	public void assignDepartments(BlockingQueue<Department> departments) {
		this.departments = departments;		
	}
	
	/* (non-Javadoc)
	 * @see department.Messanger#getAllDepartments()
	 */
	@Override
	public BlockingQueue<Department> getAllDepartments() {
		return departments;
	}

	/* (non-Javadoc)
	 * @see department.Messanger#getDepartment(int)
	 */
	@Override
	public Department getDepartment(int deptId) {
		for (Department d : departments) {
			if(Integer.parseInt(d.departmentDetails().getDeptID()) == deptId)
				return d;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see department.Messanger#getDepartment(java.lang.String)
	 */
	@Override
	public Department getDepartment(String deptName) {
		for (Department d : departments) {
			if(d.departmentDetails().getDeptName().compareTo(deptName) == 0)
				return d;
		}
		return null;
	}

}
