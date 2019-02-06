/**
 * 
 */
package pojos;

import java.io.Serializable;

/**
 * @author Brown
 *
 */
public class CarDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String model_vin;
	private String manufacturer_id; //change back to long????????????????????????????????????????????????????
	private String model_name;
	
	public String getModel_vin() {
		return model_vin;
	}
	public void setModel_vin(String model_vin) {
		this.model_vin = model_vin;
	}
	public String getManufacturer_id() {
		return manufacturer_id;
	}
	public void setManufacturer_id(String manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
}
