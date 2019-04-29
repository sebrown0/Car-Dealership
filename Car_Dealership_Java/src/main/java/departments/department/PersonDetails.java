package departments.department;

public interface PersonDetails {

	String getFirstName();

	String getLastName();
	
	String getFullName();
	
	long getID();
	
	void setFirstName(String firstName);
	
	void setLastName(String lastName);
	
	void setID(long id);
}