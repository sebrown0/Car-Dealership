package enums;

public enum Files {

	CAR_STOCK("src/main/resources/data/car_stock.json");
	
	private String fPath;
	
	Files(String fp) {
		this.fPath = fp;
	}
	
	public String filePath() {
		return this.fPath;
	}
}