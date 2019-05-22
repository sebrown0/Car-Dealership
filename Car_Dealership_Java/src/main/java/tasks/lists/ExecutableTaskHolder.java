package tasks.lists;

import tasks.abstract_tasks.Task;

/**
 * 
 * @author Steve Brown
 *
 */
public class ExecutableTaskHolder extends TaskHolder implements ExecutableTaskList{

	@Override
	public void addExecutableTask(Task task) {
		taskList.add(task);
	}
}
