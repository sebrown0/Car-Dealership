/**
 * 
 */
package utils.simulator;


import java.util.Random;

import database.ConnectionPool;
import enums.DepartmentNames;
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
			} else if(count == 5) {
				int max = MAX_COUNT - 5;
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.GARAGE_SERVICES, 1, nextRandomNumber(max));
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.GARAGE_SERVICES, 1, nextRandomNumber(max));		
				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.GARAGE_SERVICES, 1, nextRandomNumber(max));		
				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.SALES, 1, 8);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, 9);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ACCOUNTS, 1, 10);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ORDER, 1, 11);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.STOCK, 1, 12);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 13);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, 14);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, nextRandomNumber(max));
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, 16);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, nextRandomNumber(max));
				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, 19);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, nextRandomNumber(max));

			} else if(count == 6) {
				Simulations.StockCheckTest.executeTest(1, 12);
				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 15);
			} else if(count == 7) {
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ACCOUNTS, 1, 12);
			} else if(count == 8) {
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.SALES, 1, 12);
			} else if(count == 9) {
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 12);
			} else if(count == MAX_COUNT - 2) {
//				Simulations.StockCheckTest.executeTest(1, 12);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ORDER, 1, 1);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ORDER, 1, 1);
			}else if(count == MAX_COUNT) {
				if(!alreadyStoppingSimulator) { stopSimulator(); }
			}		
		}else{
			if(!alreadyStoppingSimulator) { stopSimulator(); }
		}
	}

	public static int nextRandomNumber(int max) {
		return new Random().nextInt(max) + 1;
	}
	
	private void stopSimulator() {
		alreadyStoppingSimulator = true;
		headOffice.appLog().logEntry(this, "Stopping Simulator");
		ConnectionPool.closeDS();
		headOffice.updateObserver(ObserverMessage.STOPPING);
	}
}
