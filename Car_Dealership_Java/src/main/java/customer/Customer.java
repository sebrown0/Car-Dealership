/**
 * 
 */
package customer;

import hr_department.Person;
import sales_deptartment.SalesPerson;
import stock_department.CarDetails;

/**
 * @author Steve Brown
 * Wrapper class for Customer details.
 */
public class Customer extends Person{

	private Details details = new Details();
	private Requirements customerRequirements = new Requirements();
	private SalesPerson salesPerson;
	
//	public Customer(String firstName, String lastName) {
//		this.details.setFirst_name(firstName);
//		this.details.setLast_name(lastName);
//	}
	
	public Customer(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
		this.details.setFirst_name(firstName);
		this.details.setLast_name(lastName);
	}
		
	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Requirements getCustomerRequirements() {
		return customerRequirements;
	}

	public void setCustomerRequirements(Requirements customerRequirements) {
		this.customerRequirements = customerRequirements;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public static class Details{
		
		private long customer_id;
		private String first_name;
		private String last_name;
		
		public long getCustomer_id() {
			return customer_id;
		}
		
		public void setCustomer_id(long customer_id) {
			this.customer_id = customer_id;
		}
		
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
	}
	

	public class Requirements{
		private double budget;
		private CarDetails carDetails;
			
		public CarDetails getCarDetails() {
			return carDetails;
		}

		public void setCarDetails(CarDetails carDetails) {
			this.carDetails = carDetails;
		}

		public double getBudget() {
			return budget;
		}

		public void setBudget(double budget) {
			this.budget = budget;
		}
	}
}
