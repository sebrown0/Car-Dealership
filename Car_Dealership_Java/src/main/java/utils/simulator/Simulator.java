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
	private boolean alreadyStoppingSimulator = false;
	private static final int MAX_COUNT = 40;
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
				if(!alreadyStoppingSimulator)
					stopSimulator();
			}		
		}else{
			if(!alreadyStoppingSimulator)
				stopSimulator();
		}
	}

	private void stopSimulator() {
		alreadyStoppingSimulator = true;
		headOffice.appLog().logEntry(this, "Stopping Simulator");
		ConnectionPool.closeDS();
		headOffice.updateObserver(ObserverMessage.STOPPING);
	}
}
