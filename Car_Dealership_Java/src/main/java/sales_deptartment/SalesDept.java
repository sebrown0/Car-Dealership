package sales_deptartment;

import department.Department;

/*
 *  TODO - Update comments
 */
public class SalesDept extends Department { 

	private static final String objId = "<Sales-Dept>";

	public SalesDept() {
		super(objId);
		
		// Get available sales team. 		// TODO - Random. 
		team().addEmployee(new SalesPerson(13, "Homer", "Simpson", ""));
		team().addEmployee(new SalesPerson(11, "Clint", "Eastwood", ""));
	}

}
