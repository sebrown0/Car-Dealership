package application;

import department.DepartmentTasks;
import department_tasks.NewLeadTask;
import department_tasks.StockCheckTask;
import department_tasks.Task;
import sales_deptartment.SalesDept;
import stock_department.StockDept;
import utils.Log;
import utils.Logger;

public class App {
	private static final String objId = "<Application>";
	
	public static void main(String[] args) {

		// TODO - Hadoop path
		System.setProperty("hadoop.home.dir", "C:\\hadoop");


		Log l = new Logger(true);
		l.write(objId, "App Started");
		
		StockDept stockDept = new StockDept();
		SalesDept salesDept = new SalesDept();
		
		DepartmentTasks stockTask = new StockCheckTask(stockDept);
		Task t1 = new Task(stockTask);
		t1.run();
		
		DepartmentTasks salesTask = new NewLeadTask(salesDept);
		Task t2 = new Task(salesTask);
		t2.run();
		
		
//		salesDept.newLead();
		
		
		l.write(objId, "App Ended");
		
	}

}
