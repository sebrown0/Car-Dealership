package people.customer;

import departments.stock.Car;

public interface CustomerRequirements {

	Car getCarDetails();

	void setCarDetails(Car carDetails);

	double getBudget();

	void setBudget(double budget);
}