/**
 * 
 */
package utils.simulator;


import database.ConnectionPool;
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
	private static final int MAX_COUNT = 30;
	private static final String dealerName = "Fiat";
	private HeadOffice headOffice;
			
	public Simulator(HeadOffice headOffice) {
		this.headOffice = headOffice;
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
		if(msg != ObserverMessage.STOPPING) {
			count++;
			if(count == 3) {
				Simulations.CreateDealerTest.executeTest();
			} else if(count == 6) {
				Simulations.ScheduledTaskTest.executeTest(1, 5);
			} else if(count == 15) {
				Simulations.StockCheckTest.executeTest(1, 5);
			}else if(count == MAX_COUNT) {
				stopSimulator();
			}		
		}else{
			stopSimulator();
		}
	}

	private void stopSimulator() {
		headOffice.appLog().logEntry(this, "Stopping Simulator");
		ConnectionPool.getInstance().closeDS();
		headOffice.updateObserver(ObserverMessage.STOPPING);
	}
}
