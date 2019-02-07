
package sales_deptartment;

import pojos.Customer;
import pojos.Order;

/**
 * @author Steve Brown
 * Rules for creating a new customer order.
 */
public interface CustomerOrder {
	Order takeOrder(Customer customer, Order customersOrder);
}
