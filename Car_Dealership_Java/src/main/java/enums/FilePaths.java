package enums;

public enum FilePaths {

	CAR_STOCK_PATH("src/main/resources/data/"),		// Stock files.
	LOG_PATH("C:\\Users\\Brown\\eclipse-workspace\\Car_Dealership\\logs\\");			// Log files.
	
	private String fPath;
	
	FilePaths(String fp) {
		this.fPath = fp;
	}
	
	public String filePath() {
		return this.fPath;
	}
}
