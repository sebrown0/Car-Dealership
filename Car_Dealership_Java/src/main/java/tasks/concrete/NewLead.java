package tasks.concrete;

import departments.department.Department;
import object_details.PersonDetails;
import people.employees.Employee;
import people.employees.SalesPerson;
import tasks.abstract_tasks.AtomicTask;
import tasks.abstract_tasks.IsAnExtendibleTask;
import tasks.injectors.AtomicTaskInjector;
import tasks.injectors.MeetCustomerInjector;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 *	Salesperson meets a new lead (customer).
 */
public class NewLead extends AtomicTask implements IsAnExtendibleTask {
	
	private PersonDetails newLead;
	
	public NewLead(Department tasksDepartment) {
		super(tasksDepartment);
	}

	public void details(PersonDetails newLead) {
		this.newLead = newLead;
	}
	
	@Override
	public void executeTask() {
		tasksDepartment.log().logEntry(this, String.format("[%s] has introduced themselves to New Lead [%s]",
				assignedEmployee.getFullName(), newLead.getFullName()));
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
	
	@Override
	public void visit(Employee employee) {
		this.assignedEmployee = (SalesPerson) employee;
		executeTask();
		setFollowOnTask();
	}

	@Override
	public boolean isTaskExtendible() {
		return IsAnExtendibleTask.super.isTaskExtendible();
	}

	@Override
	public void setFollowOnTask() {
		AtomicTaskInjector injector = new MeetCustomerInjector();
		CustomerRequirements task = (CustomerRequirements) injector.getNewTask(tasksDepartment);
		task.setPerson(newLead);
		setThisTasksFollowOnTask(task);
	}
}
