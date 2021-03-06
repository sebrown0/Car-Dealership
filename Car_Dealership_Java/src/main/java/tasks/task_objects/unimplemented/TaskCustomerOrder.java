/**
 * 
 */
package tasks.task_objects.unimplemented;

import department_tasks.Task;
import departments.department.Department;
import departments.hr_department.Staff;
import departments.hr_department.StaffMember;
import departments.sales_department.SalesDepartment;

/**
 * @author Steve Brown
 *
 * Responsible for handling a new customer order.
 */
//public class TaskCustomerOrder implements Task_OLD{
public class TaskCustomerOrder extends Task { //implements NewOrder{
	
	private Department department = null;
	private final String objId;
	private Staff salesTeam = null;
	
	public TaskCustomerOrder(Department dept) {
//		this.salesTeam = dept.staff();
		this.salesTeam = dept.workingStaff();	
		this.department = dept;
		this.objId = "<" + dept.deptName() + ">" + " <" + this.getClass().getSimpleName() + ">";
		department.setObjId("<" + dept.getDeptId() + ">" + " <" + this.getClass().getSimpleName());
	}
	
	/* 
	 * A person has walked into the show room. 
	 * Introduce them to the next available salesperson and make them a Customer.
	 */
	public void newLead() { 

		StaffMember sp = salesTeam.nextEmployee();
		sp.performDuty((SalesDepartment) department);
	}

//	@Override
//	public void sendOrder() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void updateDepartment() {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void run() {		
//		newLead();		
//	}
//
//	@Override
//	public boolean blocking() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void add() {
//		department.addTask(this);	// Add the task that is THIS task to the department's task list.		
//	}
}
