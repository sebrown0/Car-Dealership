/**
 * 
 */
package department_tasks;

import java.util.ArrayList;

import database.StoredProcedure;
import database.StoredProcedure.QueryBuilder;
import enums.ErrorCodes;
import enums.SalesDeptSP;

/**
 * @author Steve Brown
 *
 */
public class StoreCustomerDetails extends Task {

	/* (non-Javadoc)
	 * @see department_tasks.Task#run()
	 */
	@Override
	public void run() {
		long customerId = -1;
		
		ArrayList<String> elements = new ArrayList<>();
		elements.add(customer.getFirstName());
		elements.add(customer.getLastName());
		
		dbDAO.dbConnect();

		// Get the next available customer id from the DB and assign it to the customer. 
		String query = QueryBuilder.build(elements, SalesDeptSP.NEW_CUSTOMER.value()); // TODO - StoredProcedure.
		StoredProcedure sp = dbDAO.executeSP(query);
		if(sp.errorCode() == ErrorCodes.NONE)
			customerId = Long.valueOf(sp.getSingleValue());
			
		customer.setId(customerId);


	}

	/* (non-Javadoc)
	 * @see department_tasks.Task#updateDepartment()
	 */
	@Override
	public void updateDepartment() {
		// TODO Auto-generated method stub

	}

}
