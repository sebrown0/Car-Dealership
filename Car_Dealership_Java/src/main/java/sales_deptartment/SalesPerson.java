/**
 * 
 */
package sales_deptartment;

import customer.Customer;
import customer.CustomerOrder;
import customer.NewCustomer;
import hr_department.Employee;
import order_deptartment.Order;
import stock_department.CarDetails;

/**
 * @author Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 */

public class SalesPerson extends Employee implements NewCustomer, CustomerOrder {
	
	public SalesPerson(long id, String firstName, String lastName, String role) {
		super(id, firstName, lastName, role);
		// TODO - Remove
	}

	public SalesPerson(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO - Remove
	}

	@Override
	public void duty() {
		// TODO - Remove
		System.out.println("A salesperson duties are:");
	}

	@Override
	public void customersDetails(Customer customer) {
		//TODO
		long customerId = 1; // Have to get this from DB
		// Update the DB Here.	
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


}
