/**
 * 
 */
package pojos;

import java.io.Serializable;

/**
 * @author Brown
 *
 */
public class CarAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
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
