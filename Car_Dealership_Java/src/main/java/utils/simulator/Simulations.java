/**
 * 
 */
package utils.simulator;

import database.MySqlDB;
import dealer.CarDealer;
import dealer_management.DealerObjects;
import dealer_management.MainDealerBuilder;
import dealer_working_day.MainDealerWorkingDay;
import departments.department.Department;
import enums.DepartmentNames;
import head_office.HeadOffice;
import heartbeat.FastHeartbeat;
import spark.Spark;
import tasks.abstract_tasks.ScheduledTask;
import tasks.details.ScheduledTime;
import tasks.details.TaskSchedule;
import tasks.injectors.ScheduledInjectorTest;
import tasks.injectors.ScheduledInjectorTest2;
import tasks.injectors.ScheduledTaskInjector;
import tasks.injectors.UpdateStockInjector;
import tasks.scheduler.TaskManager;
import time.Time;

/**
 * @author Steve Brown
 *
 *	Creates different test scenarios for a specified Car Dealer.
 */
public class Simulations {

	protected static HeadOffice headOffice;
	protected static String dealerName;
	
	private Simulations() {}
	
	public static void setHeadOffice(HeadOffice hOffice) {
		headOffice = hOffice;
	}
	
	public static void setDealerName(String dName) {
		dealerName = dName;
	}
	
	public static class CreateDealerTest{
		
		protected static void executeTest() {
			headOffice.management().createNewDealer(
				new MainDealerBuilder() {},
				dealerName, 
				new MainDealerWorkingDay(new Time(9, 00, 04), new Time(9, 00, 9)),
				new DealerObjects(
					new MySqlDB(headOffice.appLog()), 
					new Spark(dealerName, "local", true, headOffice.appLog()), 
					headOffice.timer(), 
					headOffice.appLog(), 
					new TaskManager(headOffice.timer(), new FastHeartbeat(dealerName + " Task Manager"), headOffice.appLog()))	 
			);
		}	
	}
	
	public static class ScheduledTaskTest{
		
		protected static void executeTest(DepartmentNames deptName, int testNum, int timeOffset) {
			int timeNow = headOffice.timer().currentTime();
			int scheduledStart = timeNow + timeOffset;
			
			CarDealer dealer = headOffice.getDealerByName(dealerName);
			if(dealer != null) {
				Department dept = dealer.getDepartmentByName(deptName.value());
				if(dept != null) {
					TaskSchedule schedule = new ScheduledTime(scheduledStart, scheduledStart + 1);
					int r = Simulator.nextRandomNumber(2);
					ScheduledTaskInjector injector = (r == 1) ? new ScheduledInjectorTest() : new ScheduledInjectorTest2();
					ScheduledTask task = injector.getNewTask(dept, schedule);
					headOffice.getTaskManager().giveTask(task);
				}
			}
		}	
	}
		
	public static class StockCheckTest{
		protected static void executeTest(int testNum, int timeOffset) {
			int timeNow = headOffice.timer().currentTime();
			int scheduledStart = timeNow + timeOffset;
			
			CarDealer dealer = headOffice.getDealerByName(dealerName);
			if(dealer != null) {
				Department dept = dealer.getDepartmentByName(DepartmentNames.STOCK.value());
				if(dept != null) {
					TaskSchedule schedule = new ScheduledTime(scheduledStart, scheduledStart + 1);
					ScheduledTaskInjector injector = new UpdateStockInjector();
					ScheduledTask task = injector.getNewTask(dept, schedule);
					headOffice.getTaskManager().giveTask(task);
				}
			}
		}			
	}
	
}
