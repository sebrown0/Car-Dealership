
package tasks.task_objects;

import departments.department.Department;
import departments.stock_department.StockCheck;
import departments.stock_department.StockDelivery;
import departments.stock_department.StockList;
import departments.stock_department.StockUpdateProcess;
import enums.ErrorCodes;
import task_strategy.TaskListVisitor;
import tasks.task_details.TaskSchedule;
import tasks.task_details.TasksDetails;
import tasks.task_super_objects.ScheduledTask;

/**
 * @author Steve Brown
 * Updates the stock if there is a new stock file.
 * Uses the 'rules' in StockUpdate and it's interface.
 */
public class TaskUpdateStock extends ScheduledTask implements StockUpdateProcess{

	public TaskUpdateStock(TasksDetails tasksDetails, Department tasksDepartment, TaskSchedule tasksSchedule) {
		super(tasksDetails, tasksDepartment, tasksSchedule);
	}

	private String objId;
	private Department department;			// THIS SHOULD BE IN TASK DETAILS
	private StockCheck stockCheck;
	private StockDelivery stockDelivery;
	private StockList stockList;
	
	/*
	 *  Return the task's id.
	 *  Make it static so that we can get it's value for comparison without instantiating.
	 */
	public static String TASK_ID() {
		return TaskUpdateStock.class.getSimpleName();
	}
	
	@Override
	public ErrorCodes checkForNewStock() {
//		stockCheck = new StockCheck(tasksDetails.tasksDepartment.spark(), tasksDepartment.database());
//		stockCheck = new StockCheck(tasksDetails.getDealerDAO());
//		return stockCheck.checkStockFile();
		return null;
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
		
	}
	
	@Override
	public void executeTask() {
		updateStock();
	}
	
	@Override
	public <T extends TaskListVisitor> void accept(T taskList) {
		taskList.addTask(this);
	}

	@Override
	public int getStartTime() {
		return getTaskStartTime();
	}
}
