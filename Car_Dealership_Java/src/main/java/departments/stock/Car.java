/**
 * 
 */
package departments.stock;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author Steve Brown
 *
 * Wrapper class containing all the objects that hold info about a Car.
 * Used with GenericBuilder to build the object.
 */
public class Car {
	private CarDetails carDetails;
	private CarAttributes carAttributes;
	private CarEnhancements carEnhancements;
	private String modelVin;
		
	public Car() {};
	
	public void setDetails(CarDetails details) {
		this.carDetails = details;
	}
	
	public void setEnhancements(CarEnhancements enh) {	
		this.carEnhancements = enh;
	}
	
	public void setAttributes(CarAttributes attr) {
		this.carAttributes = attr;
	}
			
	public String getModelVin() {
		return modelVin;
	}
	
	@Override
	public String toString() {
		return carDetails.toString() + "\n" + carAttributes.toString() + 
				"\n" + carEnhancements.toString(); 
	}
	
	/*
	 *  The model_vins need to match for all objects that make up the car.
	 *  They are used as PKs in the database.
	 */
	public boolean checkVinNumbersAreValid() {
		List<String> vins = Arrays.asList(
				carDetails.getModel_vin(), carAttributes.getModel_vin(), carEnhancements.getModel_vin());
		
		BinaryOperator<String> op = (s1,s2) -> { return (s1.compareTo(s2) == 0) ? s1 : "";	};		
		String validVin = vins.stream().filter(p -> p != null).reduce(carDetails.getModel_vin(), op);
		if(!validVin.isEmpty()) {
			modelVin = validVin;
			return true;
		}
		
		return false;
	}
		
	public void updateModelVin(String modelVin) {
		carDetails.setModel_vin(modelVin);
		carAttributes.setModel_vin(modelVin);
		carEnhancements.setModel_vin(modelVin);
	}
}