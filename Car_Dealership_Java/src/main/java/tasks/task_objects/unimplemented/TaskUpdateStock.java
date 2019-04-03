/**
 * 
 */
package tasks.task_objects.unimplemented;

import department_tasks.Task_OLD;
import departments.department.Department;
import departments.stock_department.StockCheck;
import departments.stock_department.StockDelivery;
import departments.stock_department.StockList;
import departments.stock_department.StockUpdate;
import enums.ErrorCodes;

/**
 * @author Steve Brown
 * Updates the stock if there is a new stock file.
 * Uses the 'rules' in StockUpdate and it's interface.
 */
public class TaskUpdateStock extends StockUpdate implements Task_OLD{
	
	private final String objId;
	private Department department = null;
	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
		
	public TaskUpdateStock(Department dept) {
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
	}
	
	@Override
	public ErrorCodes checkForNewStock() {
//		stockCheck = new StockCheck(department.spark(), department.database());
		return (stockCheck.checkStockFile()); 
	}

	@Override
	public ErrorCodes readStockFile() {
//		stockDelivery = new StockDelivery(department.spark(), department.database());
		return (stockDelivery.readStockFile(this.stockCheck.getStockFile()));
	}

	@Override
	public ErrorCodes updateStockList() {
//		stockList = new StockList(department.spark(), department.database(), stockCheck.getFileNum(), stockCheck.getStockFile());
		stockList.update(stockDelivery.getDelivery());
		return ErrorCodes.NONE;
	}

	public void stockCheck() {
		department.log().logEntry(objId, "Updating Stock");
		updateStock();	
	}

	@Override
	public void run() {
		stockCheck();		
	}

	@Override
	public boolean blocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add() {
//		department.addTask(this);	// Add the task that is THIS task to the department's task list.		
	}
}
