package enums;

public enum Files {

	CAR_STOCK_PATH("src/main/resources/data/");
	
	private String fPath;
	
	Files(String fp) {
		this.fPath = fp;
	}
	
	public String filePath() {
		return this.fPath;
	}
}
