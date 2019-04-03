/**
 * 
 */
package departments.hr_department;

/**
 * @author Steve Brown
 *
 */
//TODO - Do with interface instead?????
public abstract class Employee extends Person {

	private boolean empWorking = false;
	
	public Employee(long id, String firstName, String lastName, String deptId, String role) {
		super(id, firstName, lastName, deptId, role);
	}

	public Employee(String firstName, String lastName) {
		super(firstName, lastName);
	
	}

	public boolean isEmpWorking() {
		return empWorking;
	}

	public void setEmpWorking(boolean empWorking) {
		this.empWorking = empWorking;
	}

//	@Override
//	public long id() {
//		return getId();
//	}

//	@Override
//	public String firstName() {
//		return getFirstName();
//	}

//	@Override
//	public String lastName() {
//		return getLastName();
//	}

	
	public String role() {
		return getRole();
	}

	
	public String deptId() {
		return getDeptId();
	}
	
//	@Override
//	public void performDuty(Department department) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void performDuty(SalesDept department) {
//		// TODO Auto-generated method stub
//		
//	}

}
