package sales_deptartment;

import order_deptartment.OrderDept;
import pojos.Customer;
import pojos.Order;

/*
 * Handles sales and if a sale is made then the details are passed to the ordering dept.
 */
public class SalesDept {

	private SalesPerson bob = new SalesPerson(1, "Bob", "the Builder");
	
	public void customerOrder() {
		
		// New customer has walked into the showroom.
		Customer steveB = new Customer("Steve", "Brown");

		// 'Assign' salesperson to customer.
		bob.customersDetails(steveB);
		bob.customersSalesPerson(steveB);
		bob.customersRequirements(steveB);
		
		// GET ODRE AND PASS
		Order customerOrder = bob.takeOrder(steveB, new Order());
		
		OrderDept orderDept = new OrderDept();
		orderDept.newOrder(customerOrder);
	}
	
	
}
