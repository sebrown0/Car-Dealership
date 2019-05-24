/**
 * 
 */
package tasks.lists;

/**
 * @author Steve Brown
 *
 */
public enum TaskPendingType {
	NO_MANAGER ("No Department Manager"), 
	NO_EMPLOYEE ("No Employee Available"), 
	DUPLICATE ("Duplicate"), 
	WRONG_DEPT ("Wrong Department"),
	FAILED ("Failed"),
	FOLLOW_ON_EMP_BUSY("Follow on task. Employee is busy");
	
	private String reason;
	
	TaskPendingType(String reason) {
		this.reason = reason;
	}
	
	public String reason() {
		return reason;
	}
}
