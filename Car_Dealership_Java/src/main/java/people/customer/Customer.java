/**
 * 
 */
package people.customer;

import departments.stock.CarDetails;
import object_details.PersonDetails;
import people.Person;
import people.employees.SalesPerson;

/**
 * @author Steve Brown
 * Wrapper class for Customer.
 * 
 * Holds info about the customer and their order/requirements.
 */
public class Customer extends Person implements CustomerDetails {

	private CustomerRequirements customerRequirements = new Requirements();
	private SalesPerson assignedSalesPerson;
	
	public Customer(PersonDetails personDetails) {
		super(personDetails);
	}
		
	@Override
	public SalesPerson getSalesPerson() {
		return assignedSalesPerson;
	}

	@Override
	public void setSalesPerson(SalesPerson salesPerson) {
		this.assignedSalesPerson = salesPerson;
	}

	@Override
	public CustomerRequirements getCustomerRequirements() {
		return customerRequirements;
	}

	@Override
	public void setCustomerRequirements(CustomerRequirements customerRequirements) {
		this.customerRequirements = customerRequirements;
	}
	
	/*
	 * Customer's requirements when looking for a car.
	 */
	public class Requirements implements CustomerRequirements{
		private double budget;
		private CarDetails carDetails;
			
		@Override
		public CarDetails getCarDetails() {
			return carDetails;
		}

		@Override
		public void setCarDetails(CarDetails carDetails) {
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
