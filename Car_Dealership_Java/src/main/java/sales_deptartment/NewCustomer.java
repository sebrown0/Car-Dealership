/**
 * 
 */
package sales_deptartment;

import pojos.Customer;

/**
 * @author Brown
 * Rules for a new customer.
 */
public interface NewCustomer {
	// Get the customer's details.
	void customersDetails(Customer customer);
	// Capture the customer's requirements.
	void customersRequirements(Customer customer);
	// Assign a salesperson to the customer.
	void customersSalesPerson(Customer customer);
}
