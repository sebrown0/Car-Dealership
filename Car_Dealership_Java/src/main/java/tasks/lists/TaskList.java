package tasks.lists;

import java.util.List;

import tasks.abstract_tasks.Task;

/**
 * 
 * @author Steve Brown
 *
 */
public interface TaskList extends AddTask{
		
	int numberOfTasks();
	
	boolean isNotEmpty();
	
	Task getAndRemoveNextTask();
	
	Task peekAtNextTask();
	
	List<Task> getTaskList();
}
