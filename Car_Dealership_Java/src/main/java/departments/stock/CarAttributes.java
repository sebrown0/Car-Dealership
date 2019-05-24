/**
 * 
 */
package departments.stock;

import java.util.function.Supplier;

/**
 * @author Steve Brown
 *
 * Normal attributes of a car.
 * Used with GenericBuilder to build the object.
 */
public class CarAttributes implements Supplier<CarAttributes> {
	private String model_vin;
	private String colour;
	private String transmission;
	private int horsepower;
		
	public CarAttributes(String modelVin) {		
		this.model_vin = modelVin;
	}
	
	public String getModel_vin() {
		return model_vin;
	}
	
	public String getColour() {
		return colour;
	}
	
	public String getTransmission() {
		return transmission;
	}
	
	public int getHorsepower() {
		return horsepower;
	}
	
	public void setModel_vin(String model_vin) {
		this.model_vin = model_vin;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	
	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	@Override
	public CarAttributes get() {
		return this;
	}	
	
	@Override
	public String toString() {
		return String.format("CarAttributes: \t\t[model_vin = %s] [Colour = %s] [Transmission = %s] [HP = %d]", 
				getModel_vin(),getColour(), getTransmission(), getHorsepower());
	}
}
