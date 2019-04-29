package tasks.task_objects;

import departments.department.Department;
import departments.hr_department.DepartmentStaff;
import departments.sales_department.SalesDepartment;
import people.Person;
import people.employees.SalesPerson;
import task_strategy.TaskListVisitor;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.AtomicTask;

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

	public MeetCustomer(TasksDetails tasksDetails, Department tasksDepartment) {
		super(tasksDetails, tasksDepartment);
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
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return MeetCustomer.class.getSimpleName();
	}

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
		taskList.addTask(this);
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
