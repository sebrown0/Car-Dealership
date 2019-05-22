
package tasks.details;

import java.util.function.Consumer;

import people.employees.Employee;
import tasks.abstract_tasks.Task;

/**
 * @author Steve Brown
 *
 * A report on a Task's execution.
 */
public class TaskReport implements ManagerReport {
	private Employee employee;
	private Task task;
	private boolean taskComplete = false;
	private int completedAt;

	private TaskReport(Employee employee, Task task, boolean taskComplete, int completedAt) {
		this.employee = employee;
		this.task = task;
		this.taskComplete = taskComplete;
		this.completedAt = completedAt;
	}
	
	@Override
	public Employee getEmployee() {	return employee; }

	@Override
	public Task getTask() {	return task; }

	@Override
	public boolean isTaskComplete() { return taskComplete; }

	@Override
	public long getCompletedAt() { return completedAt; }

	@Override
	public String toString() {
		return String.format("Emp =  %s : Task = %s : Task Complete = %b : At Time = %d", 
				employee.getFullName(), task.objectID(), taskComplete, completedAt);
	}

	/**
	 *  Use the builder pattern with a consumer to build a 
	 *  report on an employee's execution of a task.
	 */
	public static class ReportBuilder {
		private Employee employee;
		private Task task;
		private boolean taskComplete;
		private int completedAt;

		public ReportBuilder with(Consumer<ReportBuilder> builderFunc) {
			builderFunc.accept(this);
			return this;
		}

		public ManagerReport create() {
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

		public void setCompletedAt(int completedAt) {
			this.completedAt = completedAt;
		}
	}
}
