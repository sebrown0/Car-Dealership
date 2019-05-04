package enums;

public enum Schemas {
	
	SCHEMA("car_dealership");
	
	private String schemaName;
	
	private Schemas(String s) {
		this.schemaName = s;	
	}
	
	public String value() {
		return this.schemaName;
	}
}
