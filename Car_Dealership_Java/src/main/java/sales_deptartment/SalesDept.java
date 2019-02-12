package sales_deptartment;

import customer.Customer;
import customer.NewCustomer;
import hr_department.Employee;
import hr_department.EmployeePool;
import hr_department.HRDept;
import hr_department.Workers;
import order_deptartment.Order;
import order_deptartment.OrderDept;

/*
 * Handles sales and if a sale is made then the details are passed to the ordering dept.
 */
public class SalesDept {

	private Workers salesTeam;

	public SalesDept() {
		salesTeam = new EmployeePool();
		// TODO - Get from Database.
		salesTeam.addEmployee(new SalesPerson(13, "Homer", "Simpson", ""));
		salesTeam.addEmployee(new SalesPerson(11, "Clint", "Eastwood", ""));
	}
	
	public void customerOrder() {
		
		
		// New customer has walked into the showroom.
//		Customer daenerys = new Customer("Daenerys", "Targaryen");
		Customer steve = new Customer("Steve", "Brown");
		steve.setId(1);

		// Get the next salesperson.
		
		SalesPerson sp = (SalesPerson) salesTeam.getEmployee();
		
		// 'Assign' salesperson to customer.
		sp.customersDetails(steve);
		sp.customersSalesPerson(steve);
		sp.customersRequirements(steve);
		
		// GET ODRE AND PASS
		Order customerOrder = sp.takeOrder(steve, new Order());
		
		OrderDept orderDept = new OrderDept();
		orderDept.newOrder(customerOrder);
	}


	
	
}
