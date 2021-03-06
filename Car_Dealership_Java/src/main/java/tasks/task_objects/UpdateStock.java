
package tasks.task_objects;

import departments.department.Department;
import departments.stock_department.StockCheck;
import departments.stock_department.StockDelivery;
import departments.stock_department.StockList;
import departments.stock_department.StockUpdateProcess;
import enums.ErrorCodes;
import task_strategy.TaskListVisitor;
import tasks.task_details.TaskSchedule;
import tasks.task_super_objects.ScheduledTask;

/**
 * @author Steve Brown
 * Updates the stock if there is a new stock file.
 * Uses the 'rules' in StockUpdate and it's interface.
 */
public class UpdateStock extends ScheduledTask implements StockUpdateProcess{

	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
	
	public UpdateStock(Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDepartment, tasksSchedule);
	}
	
	@Override
	public void executeTask() {
		updateStock();	// StockUpdateProcess
	}
	
	@Override
	public ErrorCodes checkForNewStock() {
		stockCheck = new StockCheck(tasksDepartment.getDealerDAO());
		return stockCheck.checkStockFile();
	}

	@Override
	public ErrorCodes readStockFile() {
		stockDelivery = new StockDelivery(tasksDepartment.getDealerDAO());
		return (stockDelivery.readStockFile(this.stockCheck.getStockFile()));
	}

	@Override
	public ErrorCodes updateStockList() {
		stockList = new StockList(tasksDepartment.getDealerDAO(), stockCheck.getFileNum(), stockCheck.getStockFile());
		stockList.update(stockDelivery.getDelivery());
		return ErrorCodes.NONE;
	}
		
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.allocateTask(this);
	}

	@Override
	public int getStartTime() {
		return getTaskStartTime();
	}
}
