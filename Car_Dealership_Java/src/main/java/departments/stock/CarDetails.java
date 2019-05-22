/**
 * 
 */
package departments.stock;

/**
 * @author Steve Brown
 *
 * Wrapper class containing the beans that hold info about a Car.
 */
public class CarDetails {
	private CarDetailsTable carDetailsTable = new CarDetailsTable();
	private CarAttributesTable carAttributesTable = new CarAttributesTable();
	private CarEnhancementsTable carEnhancementsTable = new CarEnhancementsTable();
		
	public CarDetails(String modelVin, long manId, String modelName, 
			String colour, int hp, String transmission,  
			boolean ac, boolean alloyWheels, boolean sunroof) {
	
		this.carDetailsTable.setModel_vin(modelVin);
		this.carDetailsTable.setManufacturer_id(manId);
		this.carDetailsTable.setModel_name(modelName);
		
		carAttributesTable.setModel_vin(modelVin);
		carAttributesTable.setColour(colour);
		carAttributesTable.setHorsepower(hp);
		carAttributesTable.setTransmission(transmission);

		carEnhancementsTable.setModel_vin(modelVin);
		carEnhancementsTable.setAc(ac);
		carEnhancementsTable.setAlloy_wheels(alloyWheels);
		carEnhancementsTable.setSunroof(sunroof);
	}

	public void updateModelVin(String modelVin) {
		carDetailsTable.setModel_vin(modelVin);
		carAttributesTable.setModel_vin(modelVin);
		carEnhancementsTable.setModel_vin(modelVin);
	}
	public CarDetailsTable getCarDetailsTable() {
		return carDetailsTable;
	}

	public void setCarDetailsTable(CarDetailsTable carDetailsTable) {
		this.carDetailsTable = carDetailsTable;
	}

	public CarAttributesTable getCarAttributesTable() {
		return carAttributesTable;
	}

	public void setCarAttributesTable(CarAttributesTable carAttributesTable) {
		this.carAttributesTable = carAttributesTable;
	}

	public CarEnhancementsTable getCarEnhancementsTable() {
		return carEnhancementsTable;
	}

	public void setCarEnhancementsTable(CarEnhancementsTable carEnhancementsTable) {
		this.carEnhancementsTable = carEnhancementsTable;
	}

	public static class CarDetailsTable{
		private String model_vin;
		private long manufacturer_id;
		private String model_name;
		
		public String getModel_vin() {
			return model_vin;
		}
		public void setModel_vin(String model_vin) {
			this.model_vin = model_vin;
		}
		public long getManufacturer_id() {
			return manufacturer_id;
		}
		public void setManufacturer_id(long manufacturer_id) {
			this.manufacturer_id = manufacturer_id;
		}
		public String getModel_name() {
			return model_name;
		}
		public void setModel_name(String model_name) {
			this.model_name = model_name;
		}
	}
	
	public static class CarAttributesTable{

		private String model_vin;
		private String colour;
		private String transmission;
		private int horsepower;
		public String getModel_vin() {
			return model_vin;
		}
		public void setModel_vin(String model_vin) {
			this.model_vin = model_vin;
		}
		public String getColour() {
			return colour;
		}
		public void setColour(String colour) {
			this.colour = colour;
		}
		public String getTransmission() {
			return transmission;
		}
		public void setTransmission(String transmission) {
			this.transmission = transmission;
		}
		public int getHorsepower() {
			return horsepower;
		}
		public void setHorsepower(int horsepower) {
			this.horsepower = horsepower;
		}	
	}

	public static class CarEnhancementsTable {
		
		private String model_vin;
		private boolean sunroof;
		private boolean alloy_wheels;
		private boolean ac;
		public String getModel_vin() {
			return model_vin;
		}
		public void setModel_vin(String model_vin) {
			this.model_vin = model_vin;
		}
		public boolean isSunroof() {
			return sunroof;
		}
		public void setSunroof(boolean sunroof) {
			this.sunroof = sunroof;
		}
		public boolean isAlloy_wheels() {
			return alloy_wheels;
		}
		public void setAlloy_wheels(boolean alloy_wheels) {
			this.alloy_wheels = alloy_wheels;
		}
		public boolean isAc() {
			return ac;
		}
		public void setAc(boolean ac) {
			this.ac = ac;
		}
	}
}
