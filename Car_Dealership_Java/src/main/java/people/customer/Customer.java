/**
 * 
 */
package people.customer;

import departments.department.PersonDetails;
import departments.stock_department.CarDetails;
import people.Person;
import people.employees.SalesPerson;

/**
 * @author Steve Brown
 * Wrapper class for Customer.
 * 
 * Holds info about the customer and their order/requirements.
 */
public class Customer extends Person {

	private Requirements customerRequirements = new Requirements();
	private SalesPerson assignedSalesPerson;
	
	public Customer(PersonDetails personDetails) {
		super(personDetails);
	}
		
	public SalesPerson getSalesPerson() {
		return assignedSalesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.assignedSalesPerson = salesPerson;
	}

	public Requirements getCustomerRequirements() {
		return customerRequirements;
	}

	public void setCustomerRequirements(Requirements customerRequirements) {
		this.customerRequirements = customerRequirements;
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
