package pojos;

public class CarOrderDetails {

	private CarDetails carDetails = new CarDetails();
	private CarAttributes carAttributes = new CarAttributes();
	private CarEnhancements carEnhancements = new CarEnhancements();
	
	public CarDetails getCarDetails() {
		return this.carDetails;
	}
	public void setCarDetails(CarDetails carDetails) {
		this.carDetails = carDetails;
	}
	public CarAttributes getCarAttributes() {
		return carAttributes;
	}
	public void setCarAttributes(CarAttributes carAttributes) {
		this.carAttributes = carAttributes;
	}
	public CarEnhancements getCarEnhancements() {
		return carEnhancements;
	}
	public void setCarEnhancements(CarEnhancements carEnhancements) {
		this.carEnhancements = carEnhancements;
	}
	
}
