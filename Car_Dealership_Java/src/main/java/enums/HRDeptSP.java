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

	DEPARTMENT_IDS( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`GetDepartments`() }"),
	ROLL_CALL( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`RollCall`('" + "regex" +  "') }");
	
	private String statement = "";
	
	private HRDeptSP(String stmt) {
		this.statement = stmt;
	}
	
	public String value() {
		return statement;
	}
	
}
