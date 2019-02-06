/**
 * 
 */
package order_deptartment;

import pojos.CarOrderDetails;

/**
 * @author Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDept {
	
	public void newOrder(CarOrderDetails carOrderDetails) {
		NewOrder newOrder = new NewOrder(carOrderDetails);

	}
}
