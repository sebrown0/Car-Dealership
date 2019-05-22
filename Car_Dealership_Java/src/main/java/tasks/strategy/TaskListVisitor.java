package tasks.strategy;

import tasks.abstract_tasks.AtomicTask;
import tasks.abstract_tasks.ManagementTask;
import tasks.abstract_tasks.ScheduledTask;

public interface TaskListVisitor {
	void allocateTask(AtomicTask task);
	void allocateTask(ScheduledTask task);
	void allocateTask(ManagementTask task);
}
