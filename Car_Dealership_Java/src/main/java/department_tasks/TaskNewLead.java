/**
 * 
 */
package department_tasks;

import customer.Customer;
import department.Department;
import hr_department.Staff;
import hr_department.Employee;
import hr_department.Person;
import hr_department.StaffMember;
import sales_department.SalesDept;
import sales_department.SalesPerson;

/**
 * @author Steve Brown
 *
 * Responsible for handling a customer enquiry.
 */
public class TaskNewLead implements Task{
	
	private Department department = null;
	private final String objId;
	private Staff salesTeam = null;
	
	public TaskNewLead(Department dept) {
		this.salesTeam = dept.staff();	
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
		department.addTask(this);	// Add the task that is THIS task to the department's task list.
		department.setObjId("<" + dept.getDeptId() + ">" + " <" + this.getClass().getSimpleName());
	}
	
	/* 
	 * A person has walked into the show room. 
	 * Introduce them to the next available salesperson and make them a Customer.
	 */
	public void newLead() { 

		StaffMember sp = salesTeam.nextEmployee();
		sp.performDuty((SalesDept) department);
	}

	@Override
	public void run() {		
		newLead();		
	}
}
