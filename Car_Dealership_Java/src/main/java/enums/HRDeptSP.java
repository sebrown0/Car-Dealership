/**
 * 
 */
package enums;

/**
 * @author Steve Brown
 * 
 * Text to make up callable statements for stored procedures.
 */
public enum HRDeptSP {

	/*
	*  Get dept name and id from the departments table.
	*/  
	GET_DEPARTMENTS( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`GetDepartments`() }"),
	
	/*
	 *  Get employees that are neither sick or on annual leave.
	 *  Returns:
	 *  	1. emp_id
	 *  	2. first_name
	 *  	3. last_name
	 *  	4. dept_id 
	 */
	ROLL_CALL( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`RollCall`('" + "regex" +  "') }");
	
	private String statement = "";
	
	private HRDeptSP(String stmt) {
		this.statement = stmt;
	}
	
	public String value() {
		return statement;
	}
	
}
