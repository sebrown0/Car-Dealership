/**
 * 
 */
package pojos;

import java.io.Serializable;

/**
 * @author Brown
 *
 */
public class CarEnhancements implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
