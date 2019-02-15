/**
 * 
 */
package order_deptartment;

import department.Department;

/**
 * @author Steve Brown
 * Responsible for creating and monitoring orders. 
 */
public class OrderDept extends Department {
	
	private static final String objId = "<Order-Dept>";
	
	public OrderDept() {
		super(objId);
		// TODO Auto-generated constructor stub
	}


	public void newOrder(Order carOrderDetails) {
		
		ProcessNewOrder newOrder = new ProcessNewOrder(carOrderDetails);
		newOrder.begin();
	}
}
