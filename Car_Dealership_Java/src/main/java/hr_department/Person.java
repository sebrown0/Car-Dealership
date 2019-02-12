/**
 * 
 */
package hr_department;

import customer.Customer;
import customer.NewCustomer;

/**
 * @author Steve Brown
 * 
 * POJO for anyone involved with the Dealership, i.e salesman or customer.
 */
public class Person{
	
	private long id;
	private String firstName;
	private String lastName;
	private String role;
		
	public Person(long id, String firstName, String lastName, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
