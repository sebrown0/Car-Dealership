package application;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import department.Department;
import department.DepartmentMessanger;
import department_tasks.Task;
import department_tasks.TaskCreateDepartments;
import department_tasks.TaskNewLead;
import department_tasks.TaskRollCall;
import department_tasks.TaskUpdateStock;
import enums.DepartmentNames;
import hr_department.StaffMember;
import utils.Log;
import utils.Logger;

public class App {
	private static final String objId = "<Application>";
	private static final DepartmentNames departmentNames = null;

	
	public static void main(String[] args) {

		// TODO - Do we need more than one spark session?
		// TODO - Do we need more than one DB object?
		// TODO - Hadoop path
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
		
		Log l = new Logger(true);
		l.logEntry(objId, "App Started");
		
		Task depts = new TaskCreateDepartments(new Department("0", "App")); 
		depts.run();
		
		BlockingQueue<Department> departments = ((TaskCreateDepartments) depts).getDepartments();
		
		DepartmentMessanger deptMessanger = new DepartmentMessanger(departments);

		TaskNewLead newLead = new TaskNewLead(deptMessanger.getDepartment(DepartmentNames.SALES.value()));
		TaskUpdateStock stockCheck = new TaskUpdateStock(deptMessanger.getDepartment(DepartmentNames.STOCK.value()));
		
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
		
		l.logEntry(objId, "App Ended");
		
	}

}
