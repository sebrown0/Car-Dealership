/**
 * 
 */
package sales_deptartment;

import customer.Customer;
import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import department.Department;
import hr_department.Team;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 *
 */
public class NewLead {

	private final String objId;
	
	private SparkSessionDAO spark = null;
	private DatabaseDAO dataBase = null;
	private Team salesTeam = null;
	
	private Log log = new Logger(false);
	
//	public NewLead(String objId, SparkSessionDAO spark, DatabaseDAO dataBase ) {
	public NewLead(Department dept) {
		this.objId = dept.getDeptId() + "<NewLead>";
		this.spark = dept.spark();
		this.dataBase = dept.dataBase();
		this.salesTeam = dept.team();
	}
	
	public void newLead() {
		
		// New customer has walked into the showroom.
		Customer daenerys = new Customer("Daenerys", "Targaryen Mother of Dragons and all that 99");

		// Get the next salesperson.
		SalesPerson sp = (SalesPerson) salesTeam.getEmployee();
		
		// 'Assign' salesperson to customer.
		sp.meetCustomer(daenerys, dataBase);

		log.write(objId, "New lead: " + daenerys.getFirstName() + " " + daenerys.getLastName());

	}
}
