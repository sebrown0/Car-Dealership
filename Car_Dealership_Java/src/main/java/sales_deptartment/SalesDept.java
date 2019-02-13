package sales_deptartment;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import customer.Customer;
import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import enums.TableNames;
import hr_department.EmployeePool;
import hr_department.Workers;
import order_deptartment.Order;
import order_deptartment.OrderDept;
import spark.Spark;

/*
 * Handles sales and if a sale is made then the details are passed to the ordering dept.
 */
public class SalesDept {

	private Workers salesTeam;
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;

	public SalesDept() {
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
		Customer daenerys = new Customer("Daenerys", "Targaryen Mother of Dragons 01001");

		// Get the next salesperson.
		SalesPerson sp = (SalesPerson) salesTeam.getEmployee();
		
		// 'Assign' salesperson to customer.
		sp.meetCustomer(daenerys, dataBase);

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
