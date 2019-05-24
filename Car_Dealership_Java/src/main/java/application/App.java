package application;

import departments.stock.CarDetails;
import utils.GenericBuilder;

public class App {

	public static void main(String[] args) throws InterruptedException {
	
		CarDetails details = GenericBuilder.of(CarDetails::new)
				.with(CarDetails::setModel_vin, "modelVin1")
				.with(CarDetails::setModel_name, "Focus")
				.with(CarDetails::setManufacturer, "Ford")
				.build();
		// TODO - Hadoop path
//		System.setProperty("hadoop.home.dir", "C:\\hadoop");
//				
//		HeadOffice ho = HeadOffice.getInstance(
//				new ChangeableTime(8,59,58), 
//				new DurationInSeconds(TimeUnit.SECONDS, 60));
//
//		Simulator sim = new Simulator(ho);
//		ho.registerSimulator(sim);
//		sim.start();
	}
}
