/**
 * 
 */
package people;

import object_details.PersonDetails;

/**
 * @author Steve Brown
 * 
 */
public abstract class Person implements PersonDetails {

	private PersonDetails details;

	public Person(PersonDetails personDetails) {
		this.details = personDetails;
	}

	@Override
	public void setFirstName(String firstName) {
		this.details.setFirstName(firstName); 
	}

	@Override
	public void setLastName(String lastName) {
		this.details.setLastName(lastName);
	}

	@Override
	public void setID(long id) {
		this.details.setID(id);
	}
	
	@Override
	public String getFirstName() {
		return details.getFirstName();
	}

	@Override
	public String getLastName() {
		return details.getLastName();
	}
	
	@Override
	public String getFullName() {
		return details.getFirstName() + " " + details.getLastName();
	}
	
	@Override
	public long getID() {
		return details.getID();
	}
}
