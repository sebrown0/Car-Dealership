package hr_department;

import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept implements NewEmployee{
	
	private Log log = new Logger(false);
	private static final String objId = "<HR-Dept>";

	
	@Override
	public void addEmployee(Person employee) {
		
		log.write(objId, "New employee: " + employee.getFirstName());
		
		// Update DB Here
		employee.setId(1);
	}

}
