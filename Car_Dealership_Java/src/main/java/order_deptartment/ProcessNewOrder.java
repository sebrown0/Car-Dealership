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
import database.StoredProcedure;
import enums.ErrorCodes;
import enums.ErrorCodes.ErrorHandler;
import enums.OrderDeptSP;
import enums.TableNames;
import order_deptartment.Order.OrderListTable;
import spark.Spark;
import spark.SparkDataFramefWriter;
import spark.SparkDfWriter;
import stock_department.CarDetails;
import stock_department.CarDetails.CarAttributesTable;
import stock_department.CarDetails.CarDetailsTable;
import stock_department.CarDetails.CarEnhancementsTable;
import utils.Log;
import utils.Logger;

/**
 * @author Steve Brown
 * Creates a new order from the details given by the sales dept.
 */
public class ProcessNewOrder extends OrderUpdate{
	private SparkSessionDAO spark = null;
	private DatabaseDAO dbDAO = null;
	private Order carOrderDetails = null;
	
	private Dataset<Row> carDetailsDf;
	private Dataset<Row> carEnhDf;
	private Dataset<Row> carAttrDf;
	private Dataset<Row> orderListDf;
	
	private Log log = new Logger(false);
	private static final String objId = "<Order-Dept> <Process New Order>";
	
	private long orderId = 0;
	
	public ProcessNewOrder(Order carOrderDetails, DatabaseDAO dbDAO, SparkSessionDAO spark) {
		// Create new spark session to use throughout the update process.
		this.spark = spark;
//		spark = new Spark("Order$$$$$$Dept", "local", true); TODO - Remove
		
		//Default MySql DAO. Have to set db table before using.
		this.dbDAO = dbDAO;
//		dbDAO = new MySqlDB(TableNames.NO_TABLE.tblName()); TODO - Remove
		
		this.carOrderDetails = carOrderDetails;	
	}
	
	public void begin() {
		orderUpdate();
	}
		
	@Override
	public void processOrder() {
		
		// Get the last order number so this order can be updated with next. 
		dbDAO.dbConnect();
		StoredProcedure sp = dbDAO.executeSP(OrderDeptSP.MAX_ORDER_NUMBER.value());
		
		if(sp.errorCode() == ErrorCodes.NONE) {
			String val = sp.getSingleValue();
			this.orderId = (val == null) ?  1 : Long.valueOf(val) + 1;
		}
	
		log.logEntry(objId, "Processing order (number) " + orderId);
		
		carOrderDetails.updateOrder(orderId);	
		
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
		
		log.logEntry(objId, "Placing new order (number) " + orderId);
		
		try {
			SparkDataFramefWriter dfWrite = new SparkDfWriter();
			dfWrite.writeDfToDbTable(carDetailsDf, dbDAO, TableNames.MODEL.tblName());
			dfWrite.writeDfToDbTable(carAttrDf, dbDAO, TableNames.MODEL_ATTR.tblName());
			dfWrite.writeDfToDbTable(carEnhDf, dbDAO, TableNames.MODEL_ENH.tblName());
			dfWrite.writeDfToDbTable(orderListDf, dbDAO, TableNames.ORDER_LIST.tblName());
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
