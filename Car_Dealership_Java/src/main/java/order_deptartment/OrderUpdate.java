/**
 * 
 */
package order_deptartment;

/**
 * @author Brown
 * Abstract implementation of the order process.
 */
public abstract class OrderUpdate implements OrderProcess{
		
	public void orderUpdate() {
//		acceptOrder(carOrderDetails);
		processOrder();
		placeOrder();
	}

//	public abstract void acceptOrder(CarOrderDetails carOrderDetails);
	@Override
	public abstract void processOrder();
	@Override
	public abstract void placeOrder();
	
}
