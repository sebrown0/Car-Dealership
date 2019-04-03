/**
 * 
 */
package departments.order_department;

import java.util.ArrayList;

import customer.Customer;
import customer.CustomerOrder;
import customer.NewCustomer;
import dao.DatabaseDAO;
import database.StoredProcedure;
import departments.department.Department;
import departments.hr_department.Employee;
import departments.hr_department.Person;
import departments.order_department.Order;
import departments.order_department.OrderDepartment;
import departments.sales_department.SalesDepartment;
import departments.stock_department.CarDetails;
import enums.DepartmentNames;
import enums.ErrorCodes;
import enums.SalesDeptSP;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 * Represents a TODO  in the TODO department.
 * 
 * 
 */

public class Clerk extends Employee {
	
	private DatabaseDAO dbDAO = null;
	
	private OrderDepartment orderDept = null;
	private final String objId;
	
	public Clerk(long id, String firstName, String lastName, String deptId, String role) {
		super(id, firstName, lastName, deptId, role);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public Clerk(String firstName, String lastName) {
		super(firstName, lastName);
		this.objId =  "<" + this.getClass().getSimpleName() + ">";
	}

	public void meetCustomer(Person person, DatabaseDAO dbDAO) {
		
		Customer aCustomer = new Customer(person.getFirstName(), person.getLastName());
		
//		this.customer = aCustomer;	// TODO - Change
		this.dbDAO = dbDAO;			// TODO - Change

		orderDept.log().logEntry(objId, "New lead: " + aCustomer.getFirstName() + " " + aCustomer.getLastName());
		
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
	
//	@Override
//	public void performDuty(Department department) {
//		// Over ridden by the performDuty(SalesDept department) method.
//	}
	
}
