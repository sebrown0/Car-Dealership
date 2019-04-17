/**
 * 
 */
package employees;

import customer.Customer;
import dao.DatabaseDAO;
import departments.department.Department;
import departments.hr_department.Person;
import departments.order_department.OrderDepartment;

/**
 * @author Steve Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 * If a customer places an order the salesperson is responsible for 'giving' the order to the order department.
 */

public class Clerk extends Employee {
	
	private DatabaseDAO dbDAO = null;
	private Customer customer = null;
	private OrderDepartment orderDept = null;
	
//	private Log log = new Logger(false);
	private final String objId;
	
	public Clerk(long id, String firstName, String lastName, String deptId, String role) {
		super(id, firstName, lastName, deptId, role);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public Clerk(String firstName, String lastName) {
		super(firstName, lastName);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public void meetCustomer(Person person, DatabaseDAO dbDAO) {
		
		Customer aCustomer = new Customer(person.getFirstName(), person.getLastName());
		
		this.customer = aCustomer;	// TODO - Change
		this.dbDAO = dbDAO;			// TODO - Change

//		log.logEntry(objId, "New lead: " + aCustomer.getFirstName() + " " + aCustomer.getLastName());
		
		// 'Assign' salesperson to customer.
//		customersSalesPerson();
//		
//		// Get the new customer's details and requirements.
//		customersDetails();
//		customersRequirements();
//		
//		// Take order from customer. Making this the salesperson's responsibility (not the Order Dept).
//		Order customerOrder = takeOrder(customer, new Order());
//		
//		// Give order to Order Dept.
//		OrderDept orderDept = (OrderDept) salesDept.getMessanger().getDepartment(DepartmentNames.ORDER.value());
//		orderDept.newOrder(customerOrder);

	}
	
	@Override
	public void performDuty(Department department) {
		// Over ridden by the performDuty(SalesDept department) method.
	}
	
}
