/**
 * 
 */
package departments.sales;

import java.util.ArrayList;

import dao.DatabaseDAO;
import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import enums.ErrorCodes;
import enums.SalesDeptSP;
import object_details.PersonDetails;

/**
 * @author Steve Brown
 *
 * Add the customer to the DB and get the customer's ID back. 
 */
public class StoreCustomerDetails {

	public static String writeCustomerToDB(PersonDetails person, DatabaseDAO db) {
		String customerId = "None";
		ArrayList<String> elements = new ArrayList<>();
		
		elements.add(person.getFirstName());
		elements.add(person.getLastName());
		
		String query = QueryBuilder.build(elements, SalesDeptSP.NEW_CUSTOMER.value());
		StoredProcedure sp = db.executeSP(query);

		if(sp.errorCode() == ErrorCodes.NONE)
			customerId = sp.getSingleValue();
		
		return customerId;
	}
}
