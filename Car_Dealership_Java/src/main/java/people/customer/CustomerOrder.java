
package people.customer;

import departments.order.Order;

/**
 * @author Steve Brown
 * Rules for creating a new customer order.
 */
public interface CustomerOrder {
	Order takeOrder(CustomerDetails customer, Order customersOrder);
}
