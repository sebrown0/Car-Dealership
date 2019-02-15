/**
 * 
 */
package department;

/**
 * @author Brown
 *
 */
public class NewOrder extends DepartmentTasks {

	private Department dept = null;
		
	public NewOrder(Department dept) {
		super(dept);
		this.dept = dept;
	}


	@Override
	public void runTask() {
		System.out.println("Dept ID: " + dept.getDeptId());
		System.out.println("TasK: " + dept.getDeptTask());		
	}
}
