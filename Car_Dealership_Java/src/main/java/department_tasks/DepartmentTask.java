package department_tasks;

import department.Department;
import employees.Employee;

public interface DepartmentTask {

//	DepartmentTask receiveTask(Task task);
//	DepartmentTask receiveTask(Task task, Employee employee);
//	Employee delegateTask();
//	Employee delegateTask(Employee employee);
	
	void receiveTask(Department department, Task task);
	void receiveTask(Department department, Task task, Employee employee);

	void delegateTask(Task task);
	void delegateTask(Task task, Employee employee);
}
