package tasks.scheduler;

import tasks.abstract_tasks.Task;

public interface DepartmentTaskReceiver {
	<T extends Task> void accept(T t);
}
