package department_tasks;

import department.Department;

public abstract class Task {

	protected Department department = null;
	protected EmployeeTask employee = null;

	/**
	 * @param department: The department the task is to be performed by.
	 * @param employee: The employee who will perform the task.
	 * 
	 * Use this constructor when we know the employee who will perform the task.
	 */
	public Task(EmployeeTask employee) {
//		this.department = department;
		this.employee = employee;
	}

	/**
	 * @param department: The department the task is to be performed by.
	 * 
	 * Use this constructor when we use the next available employee from this department.
	 */
	public Task() {
////		this.department = department;
	}

	public void setDepartment(Department d) {
		this.department = d;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	abstract public void run();
//	abstract public void updateDepartment();
}
