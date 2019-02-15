/**
 * 
 */
package stock_department;

import department.Department;

/**
 * @author Brown
 *
 */
public class StockDept extends Department {

	private static final String objId = "<Stock-Dept>"; 

	private UpdateStock updateStock;
	
	public StockDept() {
		super(objId);		
	}
	
	public void updateStock() {
		updateStock = new UpdateStock(objId, spark(), dataBase());
		updateStock.beginUpdate();
	}
}
