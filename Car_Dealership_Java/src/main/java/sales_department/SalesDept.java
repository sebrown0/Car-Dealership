package sales_department;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;
import department.Department;
import hr_department.Employee;
import hr_department.NewEmployee;
import hr_department.Person;

/*
 *  TODO - Update comments
 */ 
public class SalesDept extends Department{

	private List<Person> peopleBrowsing = new ArrayList<>();  // TODO - Blocking queue.
	
	public SalesDept(String deptId, String deptName) {
		super(deptId, deptName);
		
		peopleBrowsing("Daenerys", "Targaryen Mother of Dragons 1"); // TODO - Random data
	}
	
	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {				
		staff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role)); // TODO - Change to proper employee
	}
	
	// Use to add potential customers 
	public void peopleBrowsing(String firstName, String lastName) {
		log().logEntry(objId, "New lead: " + firstName + " " + lastName);
		peopleBrowsing.add(new Customer(firstName, lastName));
	}
	
	public Person nextCustomer() {
		if(!peopleBrowsing.isEmpty()) {
			return peopleBrowsing.get(0);
		}
		return null;
	}
}
