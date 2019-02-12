package order_deptartment;

import customer.Customer;
import enums.OrderStatus;
import stock_department.CarDetails;

/*
 * Wrapper class for orders: OrderListTable.
 * 
 */
public class Order {

	private OrderListTable orderListTable = new OrderListTable();
	private CarDetails carDetails;
	private Customer customer;
	private long orderId = 188889; // Get from DB ++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderListTable(OrderListTable orderListTable) {
		this.orderListTable = orderListTable;
	}

	public OrderListTable getOrderListTable() {
		return orderListTable;
	}

	public CarDetails getCarDetails() {
		return carDetails;
	}

	private String createModelVin(long customerId, long saleId, long orderId) { 
		return ("Order." + customerId  + "." + saleId + "." + orderId);
	}
	
	public void updateOrder(long customerId, long saleId) {
		String modelVin = createModelVin(customerId, saleId, orderId);
		
		carDetails.updateModelVin(modelVin);
		
		orderListTable.setModel_id(modelVin);
		orderListTable.setCustomer_id(customerId);
		orderListTable.setOrder_status_id(OrderStatus.ORDER.ordinal()); 
		orderListTable.setSales_id(saleId);		
	}
	
	public Order order(Customer customer) {
		this.carDetails = customer.getCustomerRequirements().getCarDetails();
		updateOrder(
				customer.getDetails().getCustomer_id(), 
				customer.getSalesPerson().getId()); //salesperson id
		
		return this;
	}
		
	public static class OrderListTable{
		private long order_status_id;
		private long customer_id;
		private long sales_id;
		private String model_id;
		
		public long getOrder_status_id() {
			return order_status_id;
		}
		public void setOrder_status_id(long order_status_id) {
			this.order_status_id = order_status_id;
		}
		public long getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(long customer_id) {
			this.customer_id = customer_id;
		}
		public long getSales_id() {
			return sales_id;
		}
		public void setSales_id(long sales_id) {
			this.sales_id = sales_id;
		}
		public String getModel_id() {
			return model_id;
		}
		public void setModel_id(String model_id) {
			this.model_id = model_id;
		}	
	}
	
}
