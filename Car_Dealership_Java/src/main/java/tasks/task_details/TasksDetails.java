package tasks.task_details;

import departments.department.Department;

public interface TasksDetails {
	
	Department getDepartment();

	void setDepartment(Department department);
	
	TaskSchedule getTasksSchedule();

	void setTasksSchedule(TaskSchedule taskSchedule);

//	TypeOfTask getTaskType();

//	DealerDAO getDealerDAO();
	
//	Log getLog();

	String getMsg();

	String getTaskID();

	void setTaskID(String taskID);
}