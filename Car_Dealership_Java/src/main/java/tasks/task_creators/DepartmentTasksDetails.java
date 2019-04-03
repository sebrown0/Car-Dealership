package tasks.task_creators;

import departments.department.Department;

public interface DepartmentTasksDetails extends AtomicTasksDetails {

	Department getDepartment();

	void setDepartment(Department department);

}