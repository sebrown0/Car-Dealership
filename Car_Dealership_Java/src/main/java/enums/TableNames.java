package enums;

public enum TableNames {
		 
	NO_TABLE(""),
	DEPT("department"),
	EMP_ATT("employee_attendance"),
	EMP_DATA("employee_data"),
	EMP_HOL("employee_holiday"),
	EMP_SICK("employee_sickness"),
	EMPS("employees"),
	HR("human_resources"),
	MANUFACTURER("manufacturer"),
	MODEL("model"),
	MODEL_ATTR("model_attributes"),
	MODEL_ENH("model_enhancements"),
	RAS("role_and_seniority"),
	ROLES("roles"),
	SALARY_BANDS("salary_bands"),
	SALES_DEPT("sales_dept"),
	SENIORITY("seniorities"),
	STOCK_UPDATES("stock_updates"),
	STOCK_LIST("stock_list"),
	STOCK_STATUS("stock_status"),
	CUSTOMER("Customer"),
	ORDER_LIST("order_list");
			
	private String tblName;
	
	private TableNames(String tbl) {
		this.tblName = tbl;	
	}
	
	public String tblName() {
		return this.tblName;
	}
		
}
