/**
 * 
 */
package department_tasks;

import department.Department;
import department.DepartmentTasks;
import stock_department.UpdateStock;

/**
 * @author Steve Brown
 *
 */
public class StockCheckTask extends DepartmentTasks {

	private static final String objId = "<StockCheckTask>";
	private Department dept = null;
		
	public StockCheckTask(Department dept) {
		super(dept);
		this.dept = dept;
	}


	@Override
	public void runTask() {
		dept.log().write(dept.getDeptId(objId), "Updating stock");
		UpdateStock updateStock = new UpdateStock(dept.getDeptId(), dept.spark(), dept.dataBase());
		updateStock.beginUpdate();
	}
}
