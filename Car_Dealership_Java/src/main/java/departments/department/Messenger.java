package departments.department;

import java.util.concurrent.BlockingQueue;

public interface Messenger {

	/*
	 * Assign all the departments to the messenger.
	 */
	void assignDepartments(BlockingQueue<Department> departments);
	
	/*
	 * Return all the departments in the list.
	 */
	BlockingQueue<Department> getAllDepartments();

	/*
	 * Return a department that matches the department ID.
	 * TODO - Make this more robust.
	 */
	Department getDepartment(int deptId);

	/*
	 * Return a department that matches the department name.
	 * TODO - Make this more robust.
	 */
	Department getDepartment(String deptName);

}