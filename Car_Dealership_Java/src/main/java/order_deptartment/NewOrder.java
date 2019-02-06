/**
 * 
 */
package order_deptartment;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import containers.AppContainers.ListContainer;
import dao.SparkDAO;
import database.DataBase;
import database.MySqlDB;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import pojos.CarAttributes;
import pojos.CarDetails;
import pojos.CarEnhancements;
import pojos.CarOrderDetails;
import spark.Spark;

/**
 * @author Brown
 * Creates a new order from the details given by the sales dept.
 */
public class NewOrder extends OrderUpdate{
	private SparkDAO spark;
	private DataBase dataBase;
	private CarOrderDetails carOrderDetails;
	
	Dataset<Row> carDetailsDf;
	Dataset<Row> carEnhDf;
	Dataset<Row> carAttrDf;
	
	public NewOrder(CarOrderDetails carOrderDetails) {
		// Create new spark session to use throughout the update process.
		spark = new Spark("SalesDept", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
		this.carOrderDetails = carOrderDetails;
		orderUpdate();
		
	}
	
	@Override
	public void processOrder() {
		System.out.println("Processing order");
		
		// Process the details for the car.
		CarDetails carDetails = carOrderDetails.getCarDetails();
		ListContainer<CarDetails> lcCarDetails = new ListContainer<>(carDetails);
		carDetailsDf = spark.session().createDataFrame(lcCarDetails.getList(), carDetails.getClass());
		
//		List<CarDetails> carDetailsList = new ArrayList<>();
//		carDetailsList.add(carOrderDetails.getCarDetails());		
//		List<CarDetails> carDetailsList = lc.getList();		
//		carDetailsDf = spark.session().createDataFrame(carDetailsList, carDetails.getClass());
		
		// Process the details for the car's enhancements/extras.
		CarEnhancements carEnhancements = carOrderDetails.getCarEnhancements();
		ListContainer<CarEnhancements> lcCarEnhancements = new ListContainer<>(carEnhancements);
//		List<CarEnhancements> carEnhList = new ArrayList<>();
//		carEnhList.add(carEnhancements);
				
		carEnhDf = spark.session().createDataFrame(lcCarEnhancements.getList(), carEnhancements.getClass());
		
		// Process the car's attributes.
		CarAttributes carAttributes = carOrderDetails.getCarAttributes();
		ListContainer<CarAttributes> lcCarAttributes = new ListContainer<>(carAttributes); 
//		List<CarAttributes> carAttrList = new ArrayList<>();
//		carAttrList.add(carAttributes);
		
		carAttrDf = spark.session().createDataFrame(lcCarAttributes.getList(), carAttributes.getClass());
	}

	// 
	@Override
	public void placeOrder() {
		System.out.println("Placing order");
		
			try {
				dataBase.writeDfToDBTable(carDetailsDf, TableNames.MODEL.tblName());
				dataBase.writeDfToDBTable(carAttrDf, TableNames.MODEL_ATTR.tblName());
				dataBase.writeDfToDBTable(carEnhDf, TableNames.MODEL_ENH.tblName());
			} catch (SQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BatchUpdateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage());
			}
		
	}

}
