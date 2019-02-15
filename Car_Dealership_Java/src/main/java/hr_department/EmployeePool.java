/**
 * 
 */
package hr_department;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve Brown
 *
 * A list of employees that are available for work.
 */
public class EmployeePool implements Team{
	
	private List<Employee> emp;
	
	public EmployeePool() {
		emp = new ArrayList<Employee>();
	}

	@Override
	public void addEmployee(Employee emp) {
		this.emp.add(emp);						// Add an available worker.
	}

	@Override
	public Employee getEmployee() {
		if(!emp.isEmpty()) {
			return emp.get(0);
		}
		
		return null;
	}

}
