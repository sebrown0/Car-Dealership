package tasks.concrete;

import departments.department.Department;
import object_details.ObjectDetails;
import object_details.PersonDetails;
import people.customer.Customer;
import people.customer.CustomerDetails;
import people.employees.Employee;
import people.employees.SalesPerson;
import tasks.abstract_tasks.AtomicTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 * Responsible for handling a customer enquiry.
 */
public class MeetCustomer extends AtomicTask {//implements SalesTaskVisitor {
	
//	private SalesDepartment department = null;
//	private DepartmentStaff salesTeam = null;
//	private Person person = null;
//	private Customer customer = null;
	private SalesPerson salesPerson;

	public MeetCustomer(Department tasksDepartment) {
		super(tasksDepartment);
	}
		
	/* 
	 * A person has walked into the show room. 
	 * Introduce them to the next available salesperson and make them a Customer.
	 */
	private void meetCustomer() {		

		tasksDepartment.log().logEntry(this, "New lead: " );//+ person.getFirstName() + " " + person.getLastName());
		
		// This should be passed already !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		PersonDetails person = new ObjectDetails();
		person.setFirstName("Elvis");

		// Starts as a person then becomes a customer.
		CustomerDetails customer = new Customer(person);
		
		// 'Assign' salesperson to customer.
		customer.setSalesPerson(salesPerson);
		
		
		// Get the new customer's details and requirements.
//		customersDetails();
//		customersRequirements();
		
		// Take order from customer. Making this the salesperson's responsibility (not the Order Dept).
//		Order customerOrder = takeOrder(aCustomer, new Order());
		
		// Give order to Order Dept.
//		OrderDept orderDept = (OrderDept) department.getMessanger().getDepartment(DepartmentNames.ORDER.value());
//		orderDept.newOrder(customerOrder);
	}

	@Override
	public void executeTask() {
		meetCustomer();
	}

	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}

	@Override
	public void visit(Employee employee) {
		this.salesPerson = (SalesPerson) employee;
		executeTask();
	}
	
		
//	private void customersSalesPerson(SalesPerson sp, Customer customer) {
//		department.log().logEntry(objId, 
//				sp.getFirstName() + " " + sp.getLastName()
//				+ " greets new lead " + customer.getFirstName());
//		
//		customer.setSalesPerson(sp);
//	}


}
