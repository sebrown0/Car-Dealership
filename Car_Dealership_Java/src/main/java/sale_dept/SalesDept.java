package sale_dept;

import order_deptartment.OrderDept;
import pojos.CarOrderDetails;

public class SalesDept {

	public void customerOrder() {
		
		CarOrderDetails orderDetails = new CarOrderDetails();
		String modelVin = "Order.1.2.3";
		
		orderDetails.getCarDetails().setManufacturer_id("1"); //Ford
		orderDetails.getCarDetails().setModel_name("Focus");
		orderDetails.getCarDetails().setModel_vin(modelVin);

		orderDetails.getCarAttributes().setColour("white");
		orderDetails.getCarAttributes().setHorsepower(1800);
		orderDetails.getCarAttributes().setModel_vin(modelVin);
		orderDetails.getCarAttributes().setTransmission("Manual");
		
		orderDetails.getCarEnhancements().setModel_vin(modelVin);
		orderDetails.getCarEnhancements().setAc(false);
		orderDetails.getCarEnhancements().setAlloy_wheels(true);
		orderDetails.getCarEnhancements().setSunroof(true);
		
		OrderDept orderDept = new OrderDept();
		orderDept.newOrder(orderDetails);
	}
}
