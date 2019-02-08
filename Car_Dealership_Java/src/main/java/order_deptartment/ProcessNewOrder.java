/**
 * 
 */
package order_deptartment;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import containers.AppContainers.ListContainer;
import dao.SparkDAO;
import database.Database;
import database.MySqlDB;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import pojos.CarDetails;
import pojos.CarDetails.CarAttributesTable;
import pojos.CarDetails.CarDetailsTable;
import pojos.CarDetails.CarEnhancementsTable;
import pojos.Order;
import pojos.Order.OrderListTable;
import spark.Spark;

/**
 * @author Brown
 * Creates a new order from the details given by the sales dept.
 */
public class ProcessNewOrder extends OrderUpdate{
	private SparkDAO spark;
	private Database dataBase;
	private Order carOrderDetails;
	
	Dataset<Row> carDetailsDf;
	Dataset<Row> carEnhDf;
	Dataset<Row> carAttrDf;
	Dataset<Row> orderListDf;
	
	public ProcessNewOrder(Order carOrderDetails) {
		// Create new spark session to use throughout the update process.
		spark = new Spark("SalesDept", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
		this.carOrderDetails = carOrderDetails;	
	}
	
	public void begin() {
		orderUpdate();
	}
	
	@Override
	public void processOrder() {
		System.out.println("Processing order");
		CarDetails carD = carOrderDetails.getCarDetails();
		
		// Process the details for the car.
		CarDetailsTable carDetails = carD.getCarDetailsTable();
		ListContainer<CarDetailsTable> lcCarDetails = new ListContainer<>(carDetails);
		carDetailsDf = spark.session().createDataFrame(lcCarDetails.getList(), carDetails.getClass());
		
		// Process the details for the car's enhancements/extras.
		CarEnhancementsTable carEnhancements = carD.getCarEnhancementsTable();
		ListContainer<CarEnhancementsTable> lcCarEnhancements = new ListContainer<>(carEnhancements);				
		carEnhDf = spark.session().createDataFrame(lcCarEnhancements.getList(), carEnhancements.getClass());
		
		// Process the car's attributes.
		CarAttributesTable carAttributes = carD.getCarAttributesTable();
		ListContainer<CarAttributesTable> lcCarAttributes = new ListContainer<>(carAttributes); 
		carAttrDf = spark.session().createDataFrame(lcCarAttributes.getList(), carAttributes.getClass());
		
		OrderListTable orderListTable = carOrderDetails.getOrderListTable();
		ListContainer<OrderListTable> lcOrderList = new ListContainer<Order.OrderListTable>(orderListTable);
		orderListDf = spark.session().createDataFrame(lcOrderList.getList(), orderListTable.getClass());
		
	}

	@Override
	public void placeOrder() {
		System.out.println("Placing order");
		
		try {
			dataBase.writeDfToDBTable(carDetailsDf, TableNames.MODEL.tblName());
			dataBase.writeDfToDBTable(carAttrDf, TableNames.MODEL_ATTR.tblName());
			dataBase.writeDfToDBTable(carEnhDf, TableNames.MODEL_ENH.tblName());
			dataBase.writeDfToDBTable(orderListDf, TableNames.ORDER_LIST.tblName());
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
