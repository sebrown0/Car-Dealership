/**
 * 
 */
package departments.stock;

import java.util.function.Supplier;

/**
 * @author Steve Brown
 *
 * Enhancements or extras on a car.
 * Used with GenericBuilder to build the object.
 */
public class CarEnhancements implements Supplier<CarEnhancements> {
	private String model_vin;
	private boolean sunroof;
	private boolean alloy_wheels;
	private boolean ac;
	
	public CarEnhancements(String modelVin) {
		this.model_vin = modelVin;
	}
	
	public String getModel_vin() {
		return model_vin;
	}
	
	public boolean getSunroof() {
		return sunroof;
	}
	
	public boolean getAlloy_wheels() {
		return alloy_wheels;
	}
	
	public boolean getAC() {
		return ac;
	}
	
	public void setModel_vin(String model_vin) {
		this.model_vin = model_vin;
	}
	
	public void setSunroof(boolean sunroof) {
		this.sunroof = sunroof;
	}
	
	public void setAlloy_wheels(boolean alloy_wheels) {
		this.alloy_wheels = alloy_wheels;
	}
	
	public void setAc(boolean ac) {
		this.ac = ac;
	}

	@Override
	public CarEnhancements get() {
		return this;
	}

	@Override
	public String toString() {
		return String.format("CarEnhancements: \t[model_vin = %s] [sunroof = %b] [alloy_wheels = %b]  [ac = %b]",
				getModel_vin(), getSunroof(), getAlloy_wheels(), getAC());
	}
	
}

