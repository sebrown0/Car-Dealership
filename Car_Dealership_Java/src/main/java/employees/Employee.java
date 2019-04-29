/**
 * 
 */
package employees;

import java.util.concurrent.ArrayBlockingQueue;

import departments.department.Department;
import departments.hr_department.Person;
import departments.hr_department.StaffMember;
import tasks.task_super_objects.Task;
import utils.Loggable;

/**
 * @author Steve Brown
 *
 */
public class Employee extends Person implements StaffMember, Loggable {

//	protected Task currentTask = null;
	protected ArrayBlockingQueue<Task> employeeTaskList = new ArrayBlockingQueue<>(5);
	protected Department department;

	public Employee(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails);
		this.department = department;
	}

//	public Employee(String firstName, String lastName, Department department) {
//		super(firstName, lastName);
//		this.department = department;
//	}

//	public void setTask(Task currentTask) {
//		this.currentTask = currentTask;
//	}

	public void addTask(Task task) {
		try {
			employeeTaskList.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void performTask() {
		try {
			employeeTaskList.take().run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public long id() {
		return getId();
	}

	@Override
	public String firstName() {
		return getFirstName();
	}

	@Override
	public String lastName() {
		return getLastName();
	}

	@Override
	public String role() {
		return getRole();
	}

	@Override
	public String deptId() {
		return getDeptId();
	}

	@Override
	public Department employeeDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void performDuty(Department aDepartment) {
		// TODO Auto-generated method stub

	}
}
