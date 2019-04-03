package tasks.task_creators;

import utils.Log;

public interface AtomicTasksDetails {

	TypeOfTask getTaskType();

	Log getLog();

	String getMsg();

	String getObjId();

	void setObjId(String objId);
}