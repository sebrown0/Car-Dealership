/**
 * 
 */
package utils;


import database.MySqlDB;
import dealer_management.DealerObjects;
import dealer_management.MainDealerBuilder;
import dealer_working_day.MainDealerWorkingDay;
import head_office.HeadOffice;
import heartbeat.FastHeartbeat;
import observer.Observer;
import observer.ObserverMessage;
import spark.Spark;
import task_scheduler.TaskManager;
import time.Time;
import timer.Timers;

/**
 * @author Steve Brown
 *
 * Simulates the day-to-day operation of the Car Dealership.
 */
public class Simulator implements Observer {

	private Log log;
	private Timers timer;
	private HeadOffice headOffice;
	private int count = 0;
	private final static String objId = "<Simulator>";
		
	public Simulator(HeadOffice headOffice) {
		this.log = headOffice.appLog();
		this.timer = headOffice.timer();
		this.headOffice = headOffice;
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
		if(++count == 1) 
			createDealer();
	}
	
	private void createDealer() {
		headOffice.management().createNewDealer(
				new MainDealerBuilder() {},
				"Fiat", 
				new MainDealerWorkingDay(new Time(9, 00, 04), new Time(9, 00, 9)),
				new DealerObjects(
						new MySqlDB(log), 
						new Spark("Fiat", "local", true, log), 
						headOffice.timer(), 
						log, 
						new TaskManager(timer, new FastHeartbeat("Fiat Task Manager"), log))	 
		);
	}
}
