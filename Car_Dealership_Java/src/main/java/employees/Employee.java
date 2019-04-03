/**
 * 
 */
package employees;

import java.util.concurrent.ArrayBlockingQueue;

import department_tasks.EmployeeTask;
import department_tasks.Task_NOB;
import departments.department.Department;
import departments.hr_department.Person;
import departments.hr_department.StaffMember;

/**
 * @author Steve Brown
 *
 */
public class Employee extends Person implements EmployeeTask, StaffMember {

//	protected Task currentTask = null;
	protected ArrayBlockingQueue<Task_NOB> employeeTaskList = new ArrayBlockingQueue<Task_NOB>(5);
	
	public Employee(long id, String firstName, String lastName, String deptId, String role) {
		super(id, firstName, lastName, deptId, role);
	}

	public Employee(String firstName, String lastName) {
		super(firstName, lastName);
	
	}

//	public void setTask(Task currentTask) {
//		this.currentTask = currentTask;
//	}
	
	@Override
	public void addTask(Task_NOB task) {
		try {
			employeeTaskList.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
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
