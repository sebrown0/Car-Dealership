package people.customer;

import object_details.PersonDetails;
import people.employees.SalesPerson;

public interface CustomerDetails extends PersonDetails {

	void setSalesPerson(SalesPerson salesPerson);

	SalesPerson getSalesPerson();

	void setCustomerRequirements(CustomerRequirements customerRequirements);
	
	CustomerRequirements getCustomerRequirements();
}