/**
 * 
 */
package utils;


import database.MySqlDB;
import dealer.CarDealer;
import dealer_management.DealerObjects;
import dealer_management.MainDealerBuilder;
import dealer_working_day.MainDealerWorkingDay;
import departments.department.Department;
import head_office.HeadOffice;
import heartbeat.FastHeartbeat;
import observer.Observer;
import observer.ObserverMessage;
import spark.Spark;
import task_scheduler.TaskManager;
import tasks.task_details.Details;
import tasks.task_details.ScheduledTime;
import tasks.task_details.TaskSchedule;
import tasks.task_injectors.ScheduledInjectorTest;
import tasks.task_injectors.ScheduledTaskInjector;
import tasks.task_super_objects.ScheduledTask;
import time.Time;
import timer.Timer;

/**
 * @author Steve Brown
 *
 * Simulates the day-to-day operation of the Car Dealership.
 */
public class Simulator implements Observer, Loggable {

	private Log log;
	private Timer timer;
	private HeadOffice headOffice;
	private int count = 0;
		
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
		count++;
		if(count == 1) 
			createDealer();
		else if(count == 5)
			scheduledTaskTest(1, 5);
		else if(count == 6)
			scheduledTaskTest(2, 9);
//		else if(count == 7)
//			scheduledTaskTest(3, 12);
	}
	
	private void scheduledTaskTest(int testNum, int timeOffset) {
		int timeNow = timer.currentTime();
		log.logEntry(this, "Creating (scheduled) TEST TASK");
		
		CarDealer dealer = headOffice.getDealerByName("Fiat");
		if(dealer != null) {
			Department dept = dealer.getDepartmentByName("HR");
			if(dept != null) {
				TaskSchedule schedule = new ScheduledTime(timeNow + timeOffset, timeNow + timeOffset + 2);
				ScheduledTaskInjector injector = new ScheduledInjectorTest();
				ScheduledTask task = injector.getNewTask(new Details("Scheduled TEST " + String.valueOf(testNum), String.valueOf(testNum)), dept, schedule);
				
				headOffice.getTaskManager().giveTask(task);
			}
		}
	}
	
	private void createDealer() {
		
		headOffice.management().createNewDealer(
			new MainDealerBuilder() {},
			"Fiat", 
			new MainDealerWorkingDay(new Time(9, 00, 04), new Time(9, 00, 9)),
			new DealerObjects(
				new MySqlDB(log), 
				new Spark("Fiat", "local", false, log), 
				timer, 
				log, 
				new TaskManager(timer, new FastHeartbeat("Fiat Task Manager"), log))	 
		);
	}
}
