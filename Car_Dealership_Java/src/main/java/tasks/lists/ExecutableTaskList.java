package tasks.lists;

import tasks.abstract_tasks.Task;

/**
 * 
 * @author Steve Brown
 *
 */
public interface ExecutableTaskList extends TaskList {
	void addExecutableTask(Task task);
}
