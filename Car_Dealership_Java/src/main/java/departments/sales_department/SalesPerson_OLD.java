/**
 * 
 */
package departments.sales_department;

import java.util.ArrayList;

import customer.Customer;
import customer.CustomerOrder;
import customer.NewCustomer;
import dao.DatabaseDAO;
import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import departments.hr_department.Employee_OLD;
import departments.hr_department.Person;
import departments.order_department.Order;
import departments.order_department.OrderDepartment;
import departments.stock_department.CarDetails;
import enums.DepartmentNames;
import enums.ErrorCodes;
import enums.SalesDeptSP;

/**
 * @author Steve Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 * If a customer places an order the salesperson is responsible for 'giving' the order to the order department.
 */

public class SalesPerson_OLD extends Employee_OLD implements NewCustomer, CustomerOrder {
	
	private DatabaseDAO dbDAO = null;
	private Customer customer = null;
	private SalesDepartment department = null;
	
//	private Log log = new Logger(false);
	private final String objId;
	
	public SalesPerson_OLD(long id, String firstName, String lastName, String deptId, String role) {
		super(id, firstName, lastName, deptId, role);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public SalesPerson_OLD(String firstName, String lastName) {
		super(firstName, lastName);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public void meetCustomer(Person person, DatabaseDAO dbDAO) {
		
		Customer aCustomer = new Customer(person.getFirstName(), person.getLastName());
		
		this.customer = aCustomer;	// TODO - Change
		this.dbDAO = dbDAO;			// TODO - Change

		department.log().logEntry(objId, "New lead: " + aCustomer.getFirstName() + " " + aCustomer.getLastName());
		
		// 'Assign' salesperson to customer.
		customersSalesPerson();
		
		// Get the new customer's details and requirements.
		customersDetails();
		customersRequirements();
		
		// Take order from customer. Making this the salesperson's responsibility (not the Order Dept).
		Order customerOrder = takeOrder(customer, new Order());
		
		// Give order to Order Dept.
		OrderDepartment orderDept = (OrderDepartment) department.getMessanger().getDepartment(DepartmentNames.ORDER.value());
		orderDept.newOrder(customerOrder);

	}
	
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
	
	@Override
	public void customersDetails() {
		
		long customerId = -1;
		
		ArrayList<String> elements = new ArrayList<>();
		elements.add(customer.getFirstName());
		elements.add(customer.getLastName());
		
		dbDAO.dbConnect();

		// Get the next available customer id from the DB and assign it to the customer. 
		String query = QueryBuilder.build(elements, SalesDeptSP.NEW_CUSTOMER.value()); // TODO - StoredProcedure.
		StoredProcedure sp = dbDAO.executeSP(query);
		if(sp.errorCode() == ErrorCodes.NONE)
			customerId = Long.valueOf(sp.getSingleValue());
			
		customer.setId(customerId);

	}

	@Override
	public void customersRequirements() {
		// TODO - Random data
		long budget = 80000;
		CarDetails carDetails = new CarDetails("", // Doesn't have an order id until order is placed. 
				1, "Mustang", "White", 5200, "manual", true, true, true);

		customer.getCustomerRequirements().setCarDetails(carDetails);
		customer.getCustomerRequirements().setBudget(budget);
		
	}

	@Override
	public void customersSalesPerson() {
		department.log().logEntry(objId, 
				this.getFirstName() + " " + this.getLastName()
				+ " greets new lead " + customer.getFirstName());
		
//		customer.setSalesPerson(this); // TODO
	}

	@Override
	public Order takeOrder(Customer customer, Order customersOrder) {
		return customersOrder.order(customer);
	}

}
