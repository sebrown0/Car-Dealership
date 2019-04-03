package tasks.task_creators;

import utils.Log;

/**
 * @author Steve Brown
 *
 *         TODO
 */
public class Task implements TaskConsumer {

	private AtomicTaskRunner task;
	
	public Task(AtomicTaskRunner task) {
		this.task = task;
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskConsumer#assignTask(java.lang.String)
	 */
	@Override
	public void assignTask(String msg) {
//		task.department().giveTask(this); // TODO - URGENTLY
	}

	/*
	 * (non-Javadoc)
	 * @see tasks.TaskConsumer#getTask()
	 */
	@Override
	public AtomicTaskRunner getTask() {
		return task;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.task.executeTask();
	}

}
