package tasks.strategy;

import tasks.lists.ExecutableTaskList;
import tasks.lists.ScheduledTaskList;

public interface TaskAllocatorStrategy {
	
	void setSchedulableTasks(ScheduledTaskList scheduledTasks); 

	void setExecutableTasks(ExecutableTaskList executableTasks);
}
