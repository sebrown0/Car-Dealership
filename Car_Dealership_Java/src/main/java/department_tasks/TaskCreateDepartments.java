/**
 * 
 */
package department_tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import database.StoredProcedure;
import department.Department;
import enums.ErrorCodes;
import enums.HRDeptSP;
import hr_department.HRDept;
import order_deptartment.OrderDept;
import sales_department.SalesDept;
import stock_department.StockDept;

/**
 * @author Steve Brown
 *
 *	Creates a list of departments from the Stored Procedure HRDeptSP.DEPARTMENT_IDS.
 *	Should be run at the start of each new day.
 */
public class TaskCreateDepartments implements Task{
	
	private final String objId;
	private Department department = null;
	private BlockingQueue<Department> departmentList = new ArrayBlockingQueue<>(8); // TODO - Number of depts

	public TaskCreateDepartments(Department dept) {
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
		department.addTask(this);	// Add the task that is THIS task to the department's task list.
	}

	/*
	 * Create all departments that are in the TableNames.DEPT table (except dept == none).
	 */
	public ErrorCodes createDepartments() {
		ErrorCodes error = ErrorCodes.NONE;
	
		department.log().logEntry(objId, "Creating Departments");

		department.database().dbConnect(); // TODO - Drop DB connection when finished.
		StoredProcedure sp = new StoredProcedure(HRDeptSP.DEPARTMENT_IDS.value(), department.database().dbConnection());
		sp.execute();

		if (sp.errorCode() == ErrorCodes.NONE) {

			ConcurrentHashMap<String, String> departments 
				= sp.getMapOfValues("dept_id", "dept_name"); // TODO - Use enum or map fields?

			if (!departments.isEmpty()) {
				for (String id : departments.keySet()) {
					department.log().logEntry(objId, "Creating " + departments.get(id) + " department");
					
					Department aDepartment = null;
				
					switch (id) {			// TODO - Use enum for case statements
					case "1":						
						HRDept hr = new HRDept(id, departments.get(id));
						aDepartment = hr;
						break;

					case "2":
						SalesDept sales = new SalesDept(id, departments.get(id));
						aDepartment = sales;
						break;
					
					case "6":
						StockDept stock = new StockDept(id, departments.get(id));
						aDepartment = stock;
						break;
					
					case "8":
						OrderDept order = new OrderDept(id, departments.get(id));
						aDepartment = order;
						break;
						
					default:
						break;
					}
					
					if(aDepartment != null) {
						departmentList.add(aDepartment);
					
						// Take a roll call for each new department.
						Task rollCall = new TaskRollCall(aDepartment);
//						rollCall.add();
//						rollCall.run();
					}
				}
			} else {
				error = ErrorCodes.UNKNOWN_ERROR; // TODO - Error code
				department.log().logEntry(objId, error.eCode());
			}
		}

		return error;
	}

	public BlockingQueue<Department> getDepartments() {
		// Will have to cast the receiving object to TaskCreateDepartments if instantiated as TaskRunner.
		return departmentList;
	}

	@Override
	public void run() {
		createDepartments();
	}

}
