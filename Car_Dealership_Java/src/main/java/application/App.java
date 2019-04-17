package application;

import java.util.concurrent.TimeUnit;

import enums.DepartmentNames;
import head_office.HeadOffice;
import tasks.task_reciever.Receiver;
import time.ChangeableTime;
import timer.DurationInSeconds;
import utils.Simulator;

public class App {
	private static final String objId = "<Application>";
	private static final DepartmentNames departmentNames = null;

	
	public static void main(String[] args) throws InterruptedException {

		// TODO - Do we need more than one spark session?
		// TODO - Do we need more than one DB object?

		// TODO - Hadoop path
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
				
		HeadOffice ho = HeadOffice.getInstance(
				new ChangeableTime(8,59,58), 
				new DurationInSeconds(TimeUnit.SECONDS, 100));

		Simulator sim = new Simulator(ho);
		ho.registerSimulator(sim);
		sim.start();
		
		Receiver r = new Receiver();
		
//		AtomicTaskInjector injector = new OpenDealershipInjector();
//		AtomicTaskRunner task = injector.getNewTask(new AtomicTaskDetails(TypeOfTask.ATOMIC, carDealer.getDealerDAO(), "Open Dealer", "objId"));
//		r.giveNewTask(task, new AtomicTaskReceiver());
		
		
		
		
		
//		BlockingQueue<Department> departments = today.getDepartments();
//		
//		DepartmentMessenger deptMessenger = new DepartmentMessenger(departments);
//		
//		for(Department d: departments) {		// TODO - Have the caretaker do this as a task.
//			d.assignMessenger(deptMessenger);
//		}
		
//		DepartmentTask salesTask = deptMessenger.getDepartment("Sales");
//		salesTask.receiveTask(new MeetCustomer(new Person("Harry", "Redknapp")));
		
//		SalesPerson sp = (SalesPerson) deptMessenger.getDepartment("Sales").workingStaff().nextEmployee();
//		sp.performTask();
		
		// Sales dept is given a new lead (TASK). This task should then be performed by a staff member (Salesperson).
//		Task_OLD newLead = new TaskNewLead(deptMessenger.getDepartment(DepartmentNames.SALES.value()));
//		newLead.run();
		
//		Task stockCheck = new TaskUpdateStock(deptMessenger.getDepartment(DepartmentNames.STOCK.value()));
//		stockCheck.run();
		
		
		//_______________________________________________________________________________________________________________________

		
		
		
		
		
		// Create departments and department messanger 
//		Task depts = new TaskCreateDepartments(new Department("0", "App")); 
//		depts.run();		
		
//		BlockingQueue<Department> departments = ((TaskCreateDepartments) depts).getDepartments();
		
//		DepartmentMessenger deptMessenger = new DepartmentMessenger();
//		deptMessenger.assignDepartments(departments);
		
//		Task newLead = new TaskNewLead(deptMessenger.getDepartment(DepartmentNames.SALES.value()));
//		Task stockCheck = new TaskUpdateStock(deptMessenger.getDepartment(DepartmentNames.STOCK.value()));
		
//		for(Department d: departments) {
//			d.assignMessanger(departments);
//		}
		
		// PROBLEM WITH THREADING HERE????????????
//		Department d = deptMessenger.getDepartment(DepartmentNames.SALES.value());
//		Messenger dMessanger = d.getMessanger();
		 
//		for(Department d: departments) {
//			dMessanger = d.getMessanger();
//		}
		
//		if(dMessanger != null) {
//			Task newLead = new TaskNewLead(deptMessenger.getDepartment(DepartmentNames.SALES.value()));
//			Task stockCheck = new TaskUpdateStock(deptMessenger.getDepartment(DepartmentNames.STOCK.value()));
//		}
		
		/*
		 * 	RUN TASKS SEQUENTIALLY
		 */
//		for(Department d: departments) {
//					
//			BlockingQueue<Task> deptTasks = d.getTaskList(); 
//			
//			for(Task t: deptTasks) {
//				l.logEntry("<App>", "<Running> " + t.getClass().getSimpleName());
//				t.run();
//			}
//		}
		
//		Simulator.start();
		
//		log.logEntry(objId, "Day Ended");
		
	}

}
