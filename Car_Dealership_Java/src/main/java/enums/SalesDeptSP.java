/**
 * 
 */
package enums;

/**
 * @author Steve Brown
 * 
 * Text to make up callable statements for stored procedures.
 */
public enum SalesDeptSP {

	NEW_CUSTOMER( "{ call " +  Schemas.SCHEMA.value()	+ ".`NewCustomer`('" + "regex" + "','" + "regex" + "') }");
	
	private String statement = "";
	
	private SalesDeptSP(String stmt) {
		this.statement = stmt;
	}
	
	public String value() {
		return statement;
	}
	
}
