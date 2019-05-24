/**
 * 
 */
package departments.stock;

import java.util.function.Supplier;

/**
 * @author Steve Brown
 *
 * Basic details of a car.
 * Used with GenericBuilder to build the object.
 */
public class CarDetails implements Supplier<CarDetails>{
	private String model_vin;
	private String model_name;
	private String manufacturer;
	private long manufacturer_id;
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getModel_vin() {
		return model_vin;
	}
	
	public long getManufacturer_id() {
		return manufacturer_id;
	}
	
	public String getModel_name() {
		return model_name;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public void setModel_vin(String model_vin) {
		this.model_vin = model_vin;
	}
	
	public void setManufacturer_id(long manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}
	
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	@Override
	public CarDetails get() {
		return this;
	}

	@Override
	public String toString() {
		return String.format("CarDetails: \t\t[model_vin = %s] [manufacturer_id = %s] [model_name = %s] [manufacturer=%s]", 
				getModel_vin(),	getManufacturer_id(), getModel_name(), getManufacturer());
	}
}
