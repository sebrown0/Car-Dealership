
package tasks.task_details;

import java.util.function.Consumer;

import people.employees.Employee;
import tasks.task_super_objects.Task;

/**
 * @author Steve Brown
 *
 * A report on a Task's execution.
 */
public class TaskReport {
	private Employee employee;
	private Task task;
	private boolean taskComplete = false;
	private long completedAt;

	private TaskReport(Employee employee, Task task, boolean taskComplete, long completedAt) {
		this.employee = employee;
		this.task = task;
		this.taskComplete = taskComplete;
		this.completedAt = completedAt;
	}
	
	public Employee getEmployee() {	return employee; }

	public Task getTask() {	return task; }

	public boolean isTaskComplete() { return taskComplete; }

	public long getCompletedAt() { return completedAt; }

	@Override
	public String toString() {
		String s = String.format("Emp =  %s : Task = %s : Task Complete = %b : At Time = %d", 
				employee.getFullName(), task.objectID(), taskComplete, completedAt);
		return s;
	}

	public static class ReportBuilder {
		private Employee employee;
		private Task task;
		private boolean taskComplete;
		private long completedAt;

		public ReportBuilder with(Consumer<ReportBuilder> builderFunc) {
			builderFunc.accept(this);
			return this;
		}

		public TaskReport create() {
		  return new TaskReport(employee, task, taskComplete, completedAt);
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public void setTask(Task task) {
			this.task = task;
		}

		public void setTaskComplete(boolean taskComplete) {
			this.taskComplete = taskComplete;
		}

		public void setCompletedAt(long completedAt) {
			this.completedAt = completedAt;
		}
	}
}
