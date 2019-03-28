/**
 * 
 */
package department_tasks;

import employees.SalesPerson;
import hr_department.Person;
import hr_department.Staff;
import sales_department.SalesDept;

/**
 * @author Steve Brown
 *
 * Responsible for handling a customer enquiry.
 */
public class MeetCustomer extends Task {
	
	private final String objId;
	private SalesDept department = null;
	private Staff salesTeam = null;
	private Person person = null;
//	private Customer customer = null;
	
	public MeetCustomer(Person person) {
		super();
		this.salesTeam = this.department.idleStaff();	
//		this.department = dept;
		this.objId = "<" + this.department.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
		department.setObjId("<" + this.department.getDeptId() + ">" + " <" + this.getClass().getSimpleName());
		this.person = person;
	}
	
	/* 
	 * A person has walked into the show room. 
	 * Introduce them to the next available salesperson and make them a Customer.
	 */
	@Override
	public void run() {		

		department.log().logEntry(objId, "New lead: " + person.getFirstName() + " " + person.getLastName());
		
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
	
//	private void customersSalesPerson(SalesPerson sp, Customer customer) {
//		department.log().logEntry(objId, 
//				sp.getFirstName() + " " + sp.getLastName()
//				+ " greets new lead " + customer.getFirstName());
//		
//		customer.setSalesPerson(sp);
//	}


//	@Override
//	public boolean blocking() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void add() {
//		department.addTask(this);	// Add the task that is THIS task to the department's task list.		
//	}
}
