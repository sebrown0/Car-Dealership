/**
 * 
 */
package department_tasks;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import dao.DatabaseDAO;
import database.MySqlDB;
import database.StoredProcedure;
import department.Department;
import enums.ErrorCodes;
import enums.HRDeptSP;
import hr_department.HRDept;
import order_deptartment.OrderDept;
import sales_department.SalesDept;
import stock_department.StockDept;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 *	Creates a list of departments from the Stored Procedure HRDeptSP.DEPARTMENT_IDS.
 *	Departments are the only objects that can assign tasks. 
 *	So they have to be created before any other tasks can be run.
 *	Should be run at the start of each new day.
 */
public class CreateDepartments {
	
	private final String objId;
	private BlockingQueue<Department> departmentList = new ArrayBlockingQueue<>(8); // TODO - Number of depts
	private Log log = new Logger(false);
	private DatabaseDAO dataBase  = new MySqlDB();						// TODO - Database here?;

	
	public CreateDepartments() {
		this.objId = "<" + "dept.deptName()" + ">" + " <" + this.getClass().getSimpleName() + ">";
	}
	
	/*
	 * Create all departments that are in the TableNames.DEPT table (except dept == none).
	 */
	public ErrorCodes createDepartments() {
		ErrorCodes errorCode = ErrorCodes.NONE;
	
		log.logEntry(objId, "Creating Departments");

		dataBase.dbConnect(); // TODO - Drop DB connection when finished.

		
		// Use a stored procedure to get the departments.
		StoredProcedure sp = new StoredProcedure(HRDeptSP.GET_DEPARTMENTS.value(), dataBase.dbConnection());
		sp.execute();

		if (sp.errorCode() == ErrorCodes.NONE) {

			ConcurrentHashMap<String, String> departments 
				= sp.getMapOfValues("dept_id", "dept_name"); // TODO - Use enum or map fields?

			if (!departments.isEmpty()) {
				for (String id : departments.keySet()) {
					log.logEntry(objId, "Creating " + departments.get(id) + " department");
					
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
					
					case "7":
						OrderDept order = new OrderDept(id, departments.get(id));
						aDepartment = order;
						break;
						
					default:
						break;
					}
					
					if(aDepartment != null) {	
						departmentList.add(aDepartment);	// Add the department to the list of departments.
					}
				}
			} else {
				errorCode = ErrorCodes.UNKNOWN_ERROR; // TODO - Error code
				log.logEntry(objId, errorCode.eCode());
			}
		}

		return errorCode;
	}

	public BlockingQueue<Department> getDepartments() {
		// Will have to cast the receiving object to TaskCreateDepartments if instantiated as TaskRunner.
		return departmentList;
	}
}
