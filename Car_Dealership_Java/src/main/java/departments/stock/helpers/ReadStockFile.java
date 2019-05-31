package departments.stock.helpers;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import dao.SparkSessionDAO;
import dealer_management.DealerDAO;
import spark.SparkDfReadInterface;
import spark.SparkDfReader;

public class ReadStockFile {
	
	public Dataset<Row> deliveryDf;

	public ReadStockFile(Dataset<Row> deliveryDf) {
		this.deliveryDf = deliveryDf;
	}
	
	// Read the file into a dataset.
	public static Dataset<Row> readCarDataFile(String stockFile, SparkSessionDAO spark ) {
		
		SparkDfReadInterface dfCars = new SparkDfReader(spark);
		dfCars.readFile(stockFile, "json");

		// Get local ref to car df.
		Dataset<Row> carStockDf = dfCars.getDataFrame(); 

		// Get the extras from the sub category
		Dataset<Row> extrasDf =	carStockDf.select(carStockDf.col("vin"), 
				carStockDf.col("extras.alloy_wheels"), 
				carStockDf.col("extras.ac"), 
				carStockDf.col("extras.sunroof"));

		// Merge the extras with the car
		carStockDf.drop("extras.*");
		carStockDf = carStockDf.join(extrasDf, 
				extrasDf.col("vin").equalTo(carStockDf.col("vin")))
				.drop(extrasDf.col("vin"))
				.drop(carStockDf.col("extras"));

		return carStockDf;	
	}
}