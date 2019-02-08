/**
 * 
 */
package enums;

/**
 * @author Brown
 * Key name value to be used with DB property set/get.
 */
public enum DbProperties {
		
	URL("url"), 								
	URL_AND_SCHEMA("url_schema"), 		
	USER_NAME("username"), 					
	PASSWORD("password"),					
	SCHEMA("schema"),	
	DB_TABLE("dbtable"),
	FORMAT("format");	
	
	private String prop;
	
	private DbProperties(String p) {
		this.prop = p;
	}
	
	public String value() {
		return this.prop;
	}
}
