/**
 * 
 */
package order_deptartment;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDept {
	
	public void newOrder(Order carOrderDetails) {
		
		ProcessNewOrder newOrder = new ProcessNewOrder(carOrderDetails);
		newOrder.begin();
	}
}
