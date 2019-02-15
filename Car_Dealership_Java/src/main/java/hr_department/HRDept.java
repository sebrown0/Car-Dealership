package hr_department;

import department.Department;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept extends Department implements NewEmployee{
	
	private static final String objId = "<HR-Dept>";
	
	public HRDept() {
		super(objId);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void addEmployee(Person employee) {
		
		log().write(objId, "New employee: " + employee.getFirstName());
		
		// Update DB Here
		employee.setId(1);
	}

}
