package sales_department;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;
import department.Department;
import department_tasks.Task;
import employees.Employee;
import employees.SalesPerson;
import hr_department.Person;

/*
 *  TODO - Update comments
 */ 
public class SalesDept extends Department{

	private List<Person> peopleBrowsing = new ArrayList<>();  // TODO - Blocking queue.
	
	public SalesDept(String deptId, String deptName) {
		super(deptId, deptName);
		
		peopleBrowsing("Daenerys", "Targaryen Mother of Dragons 22"); // TODO - Random data
	}
	
	@Override
	public void addDeptStaffMember(long empId, String firstName, String lastName, String deptId, String role) {				
		idleStaff().addDepStaffMember(new SalesPerson(empId, firstName, lastName, deptId, role, this)); // TODO - Change to proper employee
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

	@Override
	public void delegateTask(Task task) {
		SalesPerson salesPerson = (SalesPerson) this.idleStaff().nextEmployee();
		salesPerson.addTask(task);
		workingStaff().addDepStaffMember(salesPerson);
		System.out.println("Delegating Sales Task");// + currentTask.taskId() + " to " + workingEmployee.getEmployeeName());
		
		// TODO - ** Remove **
		salesPerson.performTask();
		
//		this.workingEmployee = new SalesPerson("Bob", "Seager"); 		// TODO - Get the next available employee
//		this.workingEmployee.setTask(currentTask);  		// In Employee
		
		
		
//		return this.workingEmployee;
	}
	
}
