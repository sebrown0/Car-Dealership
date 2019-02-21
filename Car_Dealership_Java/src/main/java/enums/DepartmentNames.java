package enums;

public enum DepartmentNames {
		 
	HR("HR"),
	SALES("Sales"),
	IT("IT"),
    ACCOUNTS("Accounts"),
	GARAGE_SERVICES("Garage Services"),
	STOCK("Stock"),
	ORDER("Order"),
    NONE("None");
			
	private String deptName;
	
	private DepartmentNames(String name) {
		this.deptName = name;	
	}
	
	public String value() {
		return this.deptName;
	}
		
}
