package people.customer;

import departments.stock.CarDetails;

public interface CustomerRequirements {

	CarDetails getCarDetails();

	void setCarDetails(CarDetails carDetails);

	double getBudget();

	void setBudget(double budget);
}