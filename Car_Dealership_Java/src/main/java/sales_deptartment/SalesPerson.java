/**
 * 
 */
package sales_deptartment;

import pojos.CarDetails;
import pojos.Customer;
import pojos.Order;

/**
 * @author Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 */
public class SalesPerson implements NewCustomer, CustomerOrder {

	private long id;
	private String firstName;
	private String lastName;
	
	public SalesPerson(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public void customersDetails(Customer customer) {
		//TODO
		long customerId = 1; // Have to get this from DB
		
		customer.getDetails().setCustomer_id(customerId);
	}
	

	@Override
	public void customersRequirements(Customer customer) {
		long budget = 80000;
		CarDetails carDetails = new CarDetails("", // Doesn't have an order id until order is placed. 
				1, "Mustang", "White", 5200, "manual", true, true, true);

		customer.getCustomerRequirements().setCarDetails(carDetails);
		customer.getCustomerRequirements().setBudget(budget);
		
	}

	@Override
	public void customersSalesPerson(Customer customer) {
		customer.setSalesPerson(this);
	}
	
	@Override
	public Order takeOrder(Customer customer, Order customersOrder) {
		return customersOrder.order(customer);
	}

	// Getters 
	public long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


}
