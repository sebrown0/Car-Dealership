/**
 * 
 */
package customer;

import hr_department.Person;
import sales_department.SalesPerson;
import stock_department.CarDetails;

/**
 * @author Steve Brown
 * Wrapper class for Customer.
 * 
 * Holds info about the customer and their order/requirements.
 */
public class Customer extends Person{

	private Details details = new Details();
	private Requirements customerRequirements = new Requirements();
	private SalesPerson salesPerson;	// Salesperson assigned to this customer.
	
	public Customer(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO - Maybe change having details and customer?
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

	/*
	 * Customer's personal details.
	 */
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
	
	/*
	 * Customer's requirements when looking for a car.
	 */
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
