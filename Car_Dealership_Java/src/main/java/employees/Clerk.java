/**
 * 
 */
package employees;

import dao.DatabaseDAO;
import departments.department.Department;
import departments.hr_department.Person;

/**
 * @author Steve Brown
 * Represents a salesperson  in the sales department.
 * Responsible for dealing with customers.
 * If a customer places an order the salesperson is responsible for 'giving' the order to the order department.
 */

public class Clerk extends Employee {
	
	public Clerk(EmployeeDetails employeeDetails, Department department) {
		super(employeeDetails, department);
	}


	public void meetCustomer(Person person, DatabaseDAO dbDAO) {
		
//		Customer aCustomer = new Customer(person.getFirstName(), person.getLastName());
		
//		log.logEntry(objId, "New lead: " + aCustomer.getFirstName() + " " + aCustomer.getLastName());
		
		// 'Assign' salesperson to customer.
//		customersSalesPerson();
//		
//		// Get the new customer's details and requirements.
//		customersDetails();
//		customersRequirements();
//		
//		// Take order from customer. Making this the salesperson's responsibility (not the Order Dept).
//		Order customerOrder = takeOrder(customer, new Order());
//		
//		// Give order to Order Dept.
//		OrderDept orderDept = (OrderDept) salesDept.getMessanger().getDepartment(DepartmentNames.ORDER.value());
//		orderDept.newOrder(customerOrder);

	}
	
	@Override
	public void performDuty(Department department) {
		// Over ridden by the performDuty(SalesDept department) method.
	}
	
}
