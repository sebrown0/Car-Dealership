/**
 * 
 */
package utils.simulator;


import head_office.HeadOffice;
import observer.Observer;
import observer.ObserverMessage;
import utils.logger.Loggable;

/**
 * @author Steve Brown
 *
 * Simulates the day-to-day operation of the Car Dealership.
 */
public class Simulator implements Observer, Loggable {

	private int count = 0;
	private static final String dealerName = "Fiat";
			
	public Simulator(HeadOffice headOffice) {
		Simulations.setHeadOffice(headOffice);
		Simulations.setDealerName(dealerName);
	}
	
	public void start() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateObserver(ObserverMessage msg) {		
		count++;
		if(count == 1) {
			Simulations.CreateDealerTest.executeTest();
		} else if(count == 3) {
			Simulations.ScheduledTaskTest.executeTest(1, 5);
		} else if(count == 5) {
			Simulations.StockCheckTest.executeTest(1, 5);
		}	
	}
}
