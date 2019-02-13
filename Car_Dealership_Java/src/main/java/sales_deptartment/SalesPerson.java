/**
 * 
 */
package sales_deptartment;

import java.util.ArrayList;

import customer.Customer;
import customer.CustomerOrder;
import customer.NewCustomer;
import dao.DatabaseDAO;
import database.StoredProcedure;
import enums.ErrorCodes;
import enums.StoredProcedures;
import hr_department.Employee;
import order_deptartment.Order;
import order_deptartment.OrderDept;
import stock_department.CarDetails;

/**
 * @author Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 */

public class SalesPerson extends Employee implements NewCustomer, CustomerOrder {
	
	private DatabaseDAO dbDAO = null;
	private Customer customer = null;
	
	public SalesPerson(long id, String firstName, String lastName, String role) {
		super(id, firstName, lastName, role);
		// TODO - Remove
	}

	public SalesPerson(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO - Remove
	}

	public void meetCustomer(Customer customer, DatabaseDAO dbDAO) {
		//TODO - Logger
		this.customer = customer;
		this.dbDAO = dbDAO;
		
		// 'Assign' salesperson to customer.
		customersSalesPerson();
		// Get the new customer's details and requirements.
		customersDetails();
		customersRequirements();
		
		// Take order from customer.
		Order customerOrder = takeOrder(customer, new Order());
		
		// Give order to Order dept.
		OrderDept orderDept = new OrderDept();
		orderDept.newOrder(customerOrder);

	}
	
	@Override
	public void duty() {
		// TODO - Remove
		System.out.println("A salesperson duties are:");
	}

	@Override
	public void customersDetails() {
		
		long customerId = -1;
		
		ArrayList<String> elements = new ArrayList<>();
		elements.add(customer.getFirstName());
		elements.add(customer.getLastName());
		
		dbDAO.dbConnect();
		
		String query = StoredProcedure.QueryBuilder.build(elements, StoredProcedures.NEW_CUSTOMER);
		StoredProcedure sp = dbDAO.executeSP(query);
		if(sp.errorCode() == ErrorCodes.NONE)
			customerId = Long.valueOf(sp.getSingleValue());
		
		// Update the DB Here.	
		customer.getDetails().setCustomer_id(customerId);
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
		customer.setSalesPerson(this);
	}

	@Override
	public Order takeOrder(Customer customer, Order customersOrder) {
		return customersOrder.order(customer);
	}


}
