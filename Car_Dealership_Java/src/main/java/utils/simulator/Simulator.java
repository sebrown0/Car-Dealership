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
			} else if(count == 5) {
				
			} else if(count == 6) {
				Simulations.MeetCustomerTest.executeTest(count, 0);
			} else if(count == 7) {
				// SIM1/SIM2/SIM3
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.SALES, 1, 8);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.IT, 1, 15);
			} else if(count == 8) {
				// SIM3/SIM5
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 16);
				// SIM6
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 5);
			} else if(count == 9) {
				// SIM1
//				Simulations.StockCheckTest.executeTest(1, 14);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 14);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 11);
				// SIM2/SIM3
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 14);
//				Simulations.StockCheckTest.executeTest(1, 14);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 11);
				// SIM4/SIM5/SIM6
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 2);
//				Simulations.StockCheckTest.executeTest(1, 4);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.ACCOUNTS, 1, 8);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.SALES, 1, 9);
				// SIM7
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 4);
//				Simulations.StockCheckTest.executeTest(1, 4);
//				Simulations.ScheduledTaskTest.executeTest(DepartmentNames.HR, 1, 5);
				
			} else if(count == MAX_COUNT - 2) {

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
