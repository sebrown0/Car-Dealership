/**
 * 
 */
package people.customer;

import departments.stock.Car;
import object_details.PersonDetails;
import people.Person;
import people.employees.SalesPerson;

/**
 * @author Steve Brown
 * 
 * Holds info about a customer and their requirements.
 */
public class Customer extends Person implements CustomerDetails {

	private CustomerRequirements customerRequirements = new Requirements();
	private SalesPerson assignedSalesPerson;
	private PersonDetails personDetails;
	
	public Customer(PersonDetails personDetails) {
		super(personDetails);
		this.personDetails = personDetails;
	}
		
	@Override
	public SalesPerson getSalesPerson() {
		return assignedSalesPerson;
	}

	@Override
	public CustomerRequirements getCustomerRequirements() {
		return customerRequirements;
	}
	
	@Override
	public void setSalesPerson(SalesPerson salesPerson) {
		this.assignedSalesPerson = salesPerson;
	}

	@Override
	public void setCustomerRequirements(CustomerRequirements customerRequirements) {
		this.customerRequirements = customerRequirements;
	}
	
	@Override
	public String toString() {
		return String.format("name [%s] ID[%d]", personDetails.getFullName(), personDetails.getID());
	}
	
	/*
	 * Customer's requirements when looking for a car.
	 */
	public static class Requirements implements CustomerRequirements{
		private double budget;
		private Car carDetails;
			
		@Override
		public Car getCarDetails() {
			return carDetails;
		}

		@Override
		public void setCarDetails(Car carDetails) {
			this.carDetails = carDetails;
		}

		@Override
		public double getBudget() {
			return budget;
		}

		@Override
		public void setBudget(double budget) {
			this.budget = budget;
		}
	}
}
