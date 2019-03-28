package application;

import java.util.concurrent.BlockingQueue;

import department.Department;
import department.DepartmentMessenger;
import department_tasks.DepartmentTask;
import department_tasks.MeetCustomer;
import employees.SalesPerson;
import enums.DepartmentNames;
import hr_department.Person;
import utils.Log;
import utils.Logger;

public class App {
	private static final String objId = "<Application>";
	private static final DepartmentNames departmentNames = null;

	
	public static void main(String[] args) throws InterruptedException {

		Log log = new Logger(true);
		// TODO - Do we need more than one spark session?
		// TODO - Do we need more than one DB object?
		// TODO - Hadoop path
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
		
		Day today = new Day();
		today.begin();
		
		BlockingQueue<Department> departments = today.getDepartments();
		
		DepartmentMessenger deptMessenger = new DepartmentMessenger(departments);
		
		for(Department d: departments) {		// TODO - Have the caretaker do this as a task.
			d.assignMessenger(deptMessenger);
		}
		
		DepartmentTask salesTask = deptMessenger.getDepartment("Sales");
		salesTask.receiveTask(new MeetCustomer(new Person("Harry", "Redknapp")));
		
		SalesPerson sp = (SalesPerson) deptMessenger.getDepartment("Sales").workingStaff().nextEmployee();
		sp.performTask();
		
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
		
		log.logEntry(objId, "Day Ended");
		
	}

}
