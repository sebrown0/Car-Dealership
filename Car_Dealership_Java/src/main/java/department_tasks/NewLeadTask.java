/**
 * 
 */
package department_tasks;

import department.Department;
import department.DepartmentTasks;
import sales_deptartment.NewLead;
import sales_deptartment.SalesDept;

/**
 * @author Steve Brown
 *
 */
public class NewLeadTask extends DepartmentTasks { 

	private static final String objId = "<NewLeadTask>";
	private Department dept = null;
		
	public NewLeadTask(Department dept) {
		super(dept);
		this.dept = dept;
	}


	@Override // TaskRunner
	public void runTask() {
		dept.log().write(dept.getDeptId(objId), "New Lead");
		NewLead lead = new NewLead(dept);
		lead.newLead();

	}
	
}
