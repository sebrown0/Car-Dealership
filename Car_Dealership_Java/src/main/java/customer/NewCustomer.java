/**
 * 
 */
package customer;

/**
 * @author Brown
 * Rules for a new customer.
 */
public interface NewCustomer {

	void customersDetails(Customer customer);			// Get the customer's details.

	void customersRequirements(Customer customer);		// Capture the customer's requirements.

	void customersSalesPerson(Customer customer);		// Assign a salesperson to the customer.
}
