
package people.customer;

import departments.order_department.Order;

/**
 * @author Steve Brown
 * Rules for creating a new customer order.
 */
public interface CustomerOrder {
	Order takeOrder(Customer customer, Order customersOrder);
}
