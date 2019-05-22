package tasks.concrete;

import departments.department.Department;
import departments.hr.DepartmentStaff;
import departments.sales.SalesDepartment;
import people.Person;
import people.employees.SalesPerson;
import tasks.abstract_tasks.AtomicTask;
import tasks.strategy.TaskListVisitor;

/**
 * @author Steve Brown
 *
 * Responsible for handling a customer enquiry.
 */
public class MeetCustomer extends AtomicTask {
	
	private SalesDepartment department = null;
	private DepartmentStaff salesTeam = null;
	private Person person = null;
//	private Customer customer = null;

	public MeetCustomer(Department tasksDepartment) {
		super(tasksDepartment);
	}
	
//	public MeetCustomer(Person person) {
//		super();
//		this.salesTeam = this.department.idleStaff();	
////		this.department = dept;
//		this.objId = "<" + this.department.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
//		department.setObjId("<" + this.department.getDeptId() + ">" + " <" + this.getClass().getSimpleName());
//		this.person = person;
//	}
	
	/* 
	 * A person has walked into the show room. 
	 * Introduce them to the next available salesperson and make them a Customer.
	 */
	private void meetCustomer() {		

		department.log().logEntry(this, "New lead: " + person.getFirstName() + " " + person.getLastName());
		
		// 'Assign' salesperson to customer.
		SalesPerson sp = (SalesPerson) salesTeam.nextEmployee();
		sp.customersSalesPerson(person);

		
		// If all goes well........new task......
		
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
	
//	private void customersSalesPerson(SalesPerson sp, Customer customer) {
//		department.log().logEntry(objId, 
//				sp.getFirstName() + " " + sp.getLastName()
//				+ " greets new lead " + customer.getFirstName());
//		
//		customer.setSalesPerson(sp);
//	}


}
