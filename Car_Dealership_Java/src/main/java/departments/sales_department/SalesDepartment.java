package departments.sales_department;

import java.util.ArrayList;
import java.util.List;

import dealer_management.DealerDAO;
import departments.department.Department;
import departments.department.DepartmentDetails;
import departments.department.EmployeeDetails;
import people.Person;
import people.employees.SalesPerson;
import tasks.task_super_objects.Task;

/*
 *  TODO - Update comments
 */ 
public class SalesDepartment extends Department{

	private List<Person> peopleBrowsing = new ArrayList<>();  
	
//	public SalesDepartment(DepartmentDetails deptDetails, DealerDAO dealerDAO) {
//		super(deptDetails, dealerDAO);
//		
//		peopleBrowsing("Daenerys", "Targaryen Mother of Dragons 22"); // TODO - Random data
//	}
	
	@Override
	public void addDeptStaffMember(EmployeeDetails employeeDetails) {				
		idleStaff().addDepStaffMember(new SalesPerson(employeeDetails, this), log); 
	}
	
	// Use to add potential customers 
	public void peopleBrowsing(String firstName, String lastName) {
		log().logEntry(this, "New lead: " + firstName + " " + lastName);
//		peopleBrowsing.add(new Customer(firstName, lastName));
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
//		salesPerson.addTask(task);
		workingStaff().addDepStaffMember(salesPerson, log);
		System.out.println("Delegating Sales Task");// + currentTask.taskId() + " to " + workingEmployee.getEmployeeName());
		
		// TODO - ** Remove **
//		salesPerson.performTask();
		
//		this.workingEmployee = new SalesPerson("Bob", "Seager"); 		// TODO - Get the next available employee
//		this.workingEmployee.setTask(currentTask);  		// In Employee
		
		
		
//		return this.workingEmployee;
	}

	@Override
	public <T extends Task> void accept(T t) {
		// TODO Auto-generated method stub
		
	}	
}
