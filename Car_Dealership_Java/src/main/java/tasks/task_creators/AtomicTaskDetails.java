/**
 * 
 */
package tasks.task_creators;

import utils.Log;

/**
 * @author Steve Brown
 *
 *	Super class that all Task Details are derived from.
 *	Provides the details for a specific task.
 */
public class AtomicTaskDetails implements AtomicTasksDetails {

	private TypeOfTask taskType;
	private Log log;
	private String msg = "None";
	private String objId = "";
		
	/**
	 * @param taskType: The type of task, i.e. ATOMIC
	 * @param log: 		App log
	 * @param msg:		Message to be logged.
	 * @param objId:	Task's calling object.
	 */
	public AtomicTaskDetails(TypeOfTask taskType, Log log, String msg, String objId) {
		this.taskType = taskType;
		this.log = log;
		this.msg = msg;
		this.objId = objId;
	}

	/* (non-Javadoc)
	 * @see tasks.TasksDetails#getTaskType()
	 */
	@Override
	public TypeOfTask getTaskType() {
		return taskType;
	}

	/* (non-Javadoc)
	 * @see tasks.TasksDetails#getLog()
	 */
	@Override
	public Log getLog() {
		return log;
	}

	/* (non-Javadoc)
	 * @see tasks.TasksDetails#getMsg()
	 */
	@Override
	public String getMsg() {
		return msg;
	}

	/* (non-Javadoc)
	 * @see tasks.TasksDetails#getObjId()
	 */
	@Override
	public String getObjId() {
		return objId;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TasksDetails#setObjId(java.lang.String)
	 */
	@Override
	public void setObjId(String objId) {
		this.objId = objId;
	}
}
