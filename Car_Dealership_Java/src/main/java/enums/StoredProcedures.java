/**
 * 
 */
package enums;

/**
 * @author Brown
 * 
 * Text to make up callable statements for stored procedures.
 */
public enum StoredProcedures {

	NEW_CUSTOMER( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`NewCustomer`('" + "regex" + "','" + "regex" + "') }");
	
	private String statement = "";
	
	private StoredProcedures(String stmt) {
		this.statement = stmt;
	}
	
	public String value() {
		return statement;
	}
	
}
