package enums;

public enum CD_Schema {
	
	SCHEMA("car_dealership");
	
	private String schemaName;
	
	private CD_Schema(String s) {
		this.schemaName = s;	
	}
	
	public String value() {
		return this.schemaName;
	}
}
