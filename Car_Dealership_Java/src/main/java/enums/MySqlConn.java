package enums;

public enum MySqlConn {

	URL("jdbc:mysql://localhost:3306/"),
	URL_AND_SCHEMA("jdbc:mysql://localhost:3306/" + CD_Schema.SCHEMA.value()),
	USERNAME("root"),
	PASSWORD("mySqL80");
	
	private String connParam;
	
	private MySqlConn(String cp){
		this.connParam = cp;
	}
	
	public String value() {
		return this.connParam;
	}
}
