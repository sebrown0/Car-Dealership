package sales_deptartment;

import customer.Customer;
import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import hr_department.EmployeePool;
import hr_department.Workers;
import spark.Spark;
import utils.Log;
import utils.Logger;

/*
 * Handles sales and if a sale is made then the details are passed to the ordering dept.
 */
public class SalesDept {

	private Workers salesTeam;
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;
	private Log log;

	private static final String objId = "<Sales-Dept>";

	public SalesDept() {
		// New log for the Sales Dept.
		log = new Logger(false);
		
		// Create new spark session to use throughout the update process.
		spark = new Spark("SalesDept", "local", true);
		
		// Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB();
		
		// Get available sales team. 
		salesTeam = new EmployeePool();
		// TODO - Get from Database.
		salesTeam.addEmployee(new SalesPerson(13, "Homer", "Simpson", ""));
		salesTeam.addEmployee(new SalesPerson(11, "Clint", "Eastwood", ""));
	}
	
	public void newLead() {
		
		// New customer has walked into the showroom.
		Customer daenerys = new Customer("Daenerys", "Targaryen Mother of Dragons 011101");

		// Get the next salesperson.
		SalesPerson sp = (SalesPerson) salesTeam.getEmployee();
		
		// 'Assign' salesperson to customer.
		sp.meetCustomer(daenerys, dataBase);

		log.write(objId, "New lead: " + daenerys.getFirstName() + " " + daenerys.getLastName());

	}

	public SparkSessionDAO getSpark() {
		return spark;
	}

	public void setSpark(SparkSessionDAO spark) {
		this.spark = spark;
	}

	public DatabaseDAO getDataBase() {
		return dataBase;
	}

	public void setDataBase(DatabaseDAO dataBase) {
		this.dataBase = dataBase;
	}
	
}
