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
import dao.DatabaseDAO;
import dao.SparkSessionDAO;
import database.MySqlDB;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.TableNames;
import order_deptartment.Order.OrderListTable;
import spark.Spark;
import spark.SparkDfWriteInterface;
import spark.SparkDfWriter;
import stock_department.CarDetails;
import stock_department.CarDetails.CarAttributesTable;
import stock_department.CarDetails.CarDetailsTable;
import stock_department.CarDetails.CarEnhancementsTable;

/**
 * @author Steve Brown
 * Creates a new order from the details given by the sales dept.
 */
public class ProcessNewOrder extends OrderUpdate{
	private SparkSessionDAO spark;
	private DatabaseDAO dataBase;
	private Order carOrderDetails;
	
	private Dataset<Row> carDetailsDf;
	private Dataset<Row> carEnhDf;
	private Dataset<Row> carAttrDf;
	private Dataset<Row> orderListDf;
	
	public ProcessNewOrder(Order carOrderDetails) {
		// Create new spark session to use throughout the update process.
		spark = new Spark("OrderDept", "local", true);
		
		//Default MySql DAO. Have to set db table before using.
		dataBase = new MySqlDB(TableNames.NO_TABLE.tblName());
		
		this.carOrderDetails = carOrderDetails;	
	}
	
	public void begin() {
		orderUpdate();
	}
	
	@Override
	public void processOrder() {
		System.out.println("Processing order"); // TODO - Logger
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
		System.out.println("Placing order"); // TODO - logger
		
		try {
			SparkDfWriteInterface dfWrite = new SparkDfWriter();
			dfWrite.writeDfToDbTable(carDetailsDf, dataBase, TableNames.MODEL.tblName());
			dfWrite.writeDfToDbTable(carAttrDf, dataBase, TableNames.MODEL_ATTR.tblName());
			dfWrite.writeDfToDbTable(carEnhDf, dataBase, TableNames.MODEL_ENH.tblName());
			dfWrite.writeDfToDbTable(orderListDf, dataBase, TableNames.ORDER_LIST.tblName());
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO - Error
			e.printStackTrace();
		} catch (BatchUpdateException e) {
			// TODO - Error
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO - Error
			ErrorHandler.checkError(ErrorCodes.DF_ERROR, e.getMessage());
		}
	}

}
