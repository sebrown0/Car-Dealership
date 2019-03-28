/**
 * 
 */
package employees;

import customer.Customer;
import dao.DatabaseDAO;
import hr_department.Person;
import sales_department.SalesDept;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 * If a customer places an order the salesperson is responsible for 'giving' the order to the order department.
 */

//public class SalesPerson extends Employee implements NewCustomer, CustomerOrder {
public class SalesPerson extends Employee { // TODO - Staffmember
	
//	private DatabaseDAO dbDAO = null;
	private Customer customer = null;
	private SalesDept department = null;
	
	private Log log = new Logger(false);
	private final String objId;
	
	// TODO - REMOVE WHEN ALL EMPLOYEES HAVE BEEN ADDED
	public SalesPerson(long id, String firstName, String lastName, String deptId, String role) { 
		super(id, firstName, lastName, deptId, role);
		
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}
	// TODO - REMOVE WHEN ALL EMPLOYEES HAVE BEEN ADDED
	
	public SalesPerson(long id, String firstName, String lastName, String deptId, String role, SalesDept department) {
		super(id, firstName, lastName, deptId, role);
		
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
		this.department = department;
	}

	public SalesPerson(String firstName, String lastName) {
		super(firstName, lastName);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public SalesPerson customersSalesPerson(Person newLead) {
		this.customer = (Customer) newLead;
		log.logEntry(objId, 
				this.getFirstName() + " " + this.getLastName()
				+ " greets new lead " + customer.getFirstName());
		
		customer.setSalesPerson(this);
		
		return this;
	}
	
//	@Override
//	public void sendOrder() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void meetCustomer(Person person, DatabaseDAO dbDAO) {
//	public void meetCustomer() {
//		
//		Person person = department.nextCustomer();
//		Customer aCustomer = new Customer(person.getFirstName(), person.getLastName());
//		
//		this.customer = aCustomer;	// TODO - Change
//		this.dbDAO = department.database();			// TODO - Change
//
//		log.logEntry(objId, "New lead: " + aCustomer.getFirstName() + " " + aCustomer.getLastName());
//		
//		// 'Assign' salesperson to customer.
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
//		OrderDept orderDept = (OrderDept) department.getMessanger().getDepartment(DepartmentNames.ORDER.value());
//		orderDept.newOrder(customerOrder);
//
//	}
//	
//	@Override
//	public void performDuty(SalesDept department) {
//		this.salesDept = department; TODO - R
//		// To have more than one duty we will have to have a list of duties.
//		meetCustomer(department.nextCustomer(), department.dataBase());
//	}
	
//	@Override
//	public void performDuty(Department aDepartment) {
//		// Over ridden by the performDuty(SalesDept department) method.
//		this.department = (SalesDept) aDepartment;
//		meetCustomer(department.nextCustomer(), department.dataBase());
//	}
//	
//	@Override
//	public void customersDetails() {
//		
//		long customerId = -1;
//		
//		ArrayList<String> elements = new ArrayList<>();
//		elements.add(customer.getFirstName());
//		elements.add(customer.getLastName());
//		
//		dbDAO.dbConnect();
//
//		// Get the next available customer id from the DB and assign it to the customer. 
//		String query = QueryBuilder.build(elements, SalesDeptSP.NEW_CUSTOMER.value()); // TODO - StoredProcedure.
//		StoredProcedure sp = dbDAO.executeSP(query);
//		if(sp.errorCode() == ErrorCodes.NONE)
//			customerId = Long.valueOf(sp.getSingleValue());
//			
//		customer.setId(customerId);
//
//	}

//	@Override
//	public void customersRequirements() {
//		// TODO - Random data
//		long budget = 80000;
//		CarDetails carDetails = new CarDetails("", // Doesn't have an order id until order is placed. 
//				1, "Mustang", "White", 5200, "manual", true, true, true);
//
//		customer.getCustomerRequirements().setCarDetails(carDetails);
//		customer.getCustomerRequirements().setBudget(budget);
//		
//	}

	
	

//	@Override
//	public Order takeOrder(Customer customer, Order customersOrder) {
//		return customersOrder.order(customer);
//	}

}
