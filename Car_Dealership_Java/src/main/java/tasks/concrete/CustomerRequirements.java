package tasks.concrete;

import departments.department.Department;
import departments.sales.StoreCustomerDetails;
import object_details.ObjectDetails;
import object_details.PersonDetails;
import people.customer.Customer;
import people.employees.Employee;
import people.employees.SalesPerson;
import tasks.abstract_tasks.AtomicTask;
import tasks.abstract_tasks.IsAnExtendibleTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 * Responsible for getting a customer requirements for a car.
 */
public class CustomerRequirements extends AtomicTask {//implements IsAnExtendibleTask {
	
	private Customer aNewCustomer;
	private PersonDetails person;
		
	public CustomerRequirements(Department tasksDepartment) {
		super(tasksDepartment);
	}
	
	public void setPerson(PersonDetails person) {
		this.person = person;
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}
	
	@Override
	public void visit(Employee employee) {
		this.assignedEmployee = (SalesPerson) employee;
		executeTask();
//		setFollowOnTask();
	}
	
	@Override
	public void executeTask() {
		changePersonIntoCustomer();
//		captureCustomerRequirements();
	}

	private void changePersonIntoCustomer() {
		String custID = StoreCustomerDetails.writeCustomerToDB(person, tasksDepartment.database());
		aNewCustomer = new Customer(person);
		aNewCustomer.setID(Long.valueOf(custID));
		tasksDepartment.log().logEntry(this, String.format("Added customer %s to the DB", aNewCustomer));
	}
	
	private void captureCustomerRequirements() {		
		tasksDepartment.log().logEntry(this,String.format("Getting %s requirements", aNewCustomer));
		
//		customersRequirements();
		
		// Take order from customer. Making this the salesperson's responsibility (not the Order Dept).
//		Order customerOrder = takeOrder(aCustomer, new Order());
		
		// Give order to Order Dept.
//		OrderDept orderDept = (OrderDept) department.getMessanger().getDepartment(DepartmentNames.ORDER.value());
//		orderDept.newOrder(customerOrder);
	}

//	@Override
//	public boolean isTaskExtendible() {
//		return IsAnExtendibleTask.super.isTaskExtendible();
//	}
//
//	@Override
//	public void setFollowOnTask() {
//		
//	}
	
}
