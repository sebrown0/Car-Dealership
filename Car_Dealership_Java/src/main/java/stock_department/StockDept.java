/**
 * 
 */
package stock_department;

/**
 * @author Brown
 *
 */
public class StockDept {

	private UpdateStock updateStock;
	
	public StockDept() {
		//		
	}
	
	public void updateStock() {
		updateStock = new UpdateStock();
		updateStock.beginUpdate();
	}
}
