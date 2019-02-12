package hr_department;

/**
 * @author Steve Brown
 *
 *         Responsible for managing people within the Car Dealership.
 * 
 */

public class HRDept implements NewEmployee{

	
	@Override
	public void addEmployee(Person employee) {
		// TODO - Logger
		System.out.println("New employee: " + employee.getFirstName());
		
		// Update DB Here
		employee.setId(1);
	}

}
