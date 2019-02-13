/**
 * 
 */
package enums;

/**
 * @author Brown
 *
 */
public enum OrderDeptSP {

	MAX_ORDER_NUMBER( "{ call " +  CD_Schema.SCHEMA.value()	+ ".`MaxOrderNumber`() }");
	
	private String statement = "";
	
	private OrderDeptSP(String stmt) {
		this.statement = stmt;
	}
	
	public String value() {
		return statement;
	}
}
